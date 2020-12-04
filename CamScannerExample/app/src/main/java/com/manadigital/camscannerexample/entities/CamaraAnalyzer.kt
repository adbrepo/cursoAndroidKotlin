package com.manadigital.camscannerexample.entities

import android.annotation.SuppressLint
import androidx.annotation.experimental.UseExperimental
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@UseExperimental(markerClass = ExperimentalGetImage::class)
class QrCodeAnalyzer(
    private val onQrCodesDetected: (qrCodes: List<Barcode>?) -> Unit
) : ImageAnalysis.Analyzer {

    private fun rotationDegreesToFirebaseRotation(rotationDegrees: Int): Int {
        return when (rotationDegrees) {
            0 -> 0
            90 -> 1
            180 -> 2
            270 -> 3
            else -> throw IllegalArgumentException("Not supported")
        }
    }

    override fun analyze(image: ImageProxy) {
        val rotation = rotationDegreesToFirebaseRotation(image.imageInfo.rotationDegrees)
        image.image?.let {
            val imageValue = InputImage.fromMediaImage(it, image.imageInfo.rotationDegrees)
            val options = BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE,Barcode.FORMAT_CODE_128).build()
            val scanner = BarcodeScanning.getClient(options)
            scanner.process(imageValue)
                .addOnCompleteListener { barcodes ->
                    barcodes.result?.forEach { barcode ->
                        val bounds = barcode.boundingBox
                        val corners = barcode.cornerPoints

                        val rawValue = barcode.rawValue
                    }
                    onQrCodesDetected(barcodes.result)
                    image.image?.close()
                    image.close()
                }
                .addOnFailureListener { failure ->
                    failure.printStackTrace()
                    image.image?.close()
                    image.close()
                }
        }
    }
}