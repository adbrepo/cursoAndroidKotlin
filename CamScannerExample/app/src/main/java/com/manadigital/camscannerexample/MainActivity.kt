package com.manadigital.camscannerexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.manadigital.camscannerexample.entities.QrCodeAnalyzer

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 10
    }

    private lateinit var textureView: PreviewView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textureView = findViewById(R.id.texture_view)

        // Request camera permissions
        if (isCameraPermissionGranted()) {
            textureView.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }
    }

    private fun startCamera() {
        var cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        val cameraSelector =
            CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        val previewConfig = Preview.Builder()
            // We want to show input from back camera of the device
            .setTargetResolution(Size(400,400))
            .build()

        previewConfig.setSurfaceProvider(textureView.createSurfaceProvider())


        val imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .setTargetResolution(Size(400,400))
            // We request aspect ratio but no resolution to match preview config, but letting
            // CameraX optimize for whatever specific resolution best fits requested capture mode
            // Set initial target rotation, we will have to call this again if rotation changes
            // during the lifecycle of this use case
            .build()
        val executor = ContextCompat.getMainExecutor(this)
        val imageAnalyzer = ImageAnalysis.Builder().build().also {
            it.setAnalyzer(executor, QrCodeAnalyzer { qrCodes ->
                qrCodes?.forEach {
                    Log.d("MainActivity", "QR Code detected: ${it.rawValue}.")
                }
            })


        }


        cameraProviderFuture.addListener(Runnable{
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                previewConfig,
                imageCapture,
                imageAnalyzer
            )

//            Handle flash
            camera.cameraControl.enableTorch(false)
        }, executor)
    }

    private fun isCameraPermissionGranted(): Boolean {
        val selfPermission =
            ContextCompat.checkSelfPermission(baseContext, Manifest.permission.CAMERA)
        return selfPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (isCameraPermissionGranted()) {
                textureView.post { startCamera() }
            } else {
                Toast.makeText(this, "Camera permission is required.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}