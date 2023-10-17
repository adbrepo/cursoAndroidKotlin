package com.utn.filepickerexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var txtFilePath: TextView
    lateinit var btnPickImage: Button
    lateinit var btnPickFile: Button

    val IMAGE_REQUEST_CODE = 50
    val FILE_REQUEST_CODE = 51

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtFilePath = findViewById(R.id.txtFilePath)
        btnPickImage = findViewById(R.id.btnPickImage)
        btnPickFile = findViewById(R.id.btnPickFile)

        btnPickImage.setOnClickListener {
            chooseImage()
        }

        btnPickFile.setOnClickListener {
            chooseGenericFile()
        }
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        //launch picker screen
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun chooseGenericFile() {
        val intent = Intent()
        intent.type = "*/*"
        intent.action = Intent.ACTION_GET_CONTENT
        //launch picker screen
        startActivityForResult(intent, FILE_REQUEST_CODE)
    }


    /**
     * Add this method to receive the result of the file picker screen
     **/
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.data != null) {
            //Get URI pointing to the file that was selected from the user
            data.data?.let { uri ->
                processFile("Image", uri)
            }
        } else if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.data != null) {
            //Get URI pointing to the file that was selected from the user
            data.data?.let { uri ->
                processFile("File", uri)
            }
        }
    }

    private fun processFile(type: String, uri: Uri) {
        //Get file extension e.g mp4, flv, pdf...
        val fileType = getFileType(uri)

        //Temporary file to hold content of actual file
        val file = File.createTempFile(type, ".$fileType")

        //Copy content from actual file to Temporary file using Input Stream
        copyInputStreamToFile(
            inputStream = contentResolver.openInputStream(uri)!!,
            file = file
        )

        //At this point the Temporary file object is ready, you can upload or use as needed
        txtFilePath.text = "Path: ${file.path} - $type"
    }

    /**
     * Get the extension of the file the provided uri points to
     **/
    private fun getFileType(uri: Uri): String? {
        val r = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(r.getType(uri))
    }


    /**
     * Copy contents from input stream to file
     **/
    @Throws(IOException::class)
    private fun copyInputStreamToFile(inputStream: InputStream, file: File) {
        try {
            FileOutputStream(file, false).use { outputStream ->
                var read: Int
                val bytes = ByteArray(DEFAULT_BUFFER_SIZE)
                while (inputStream.read(bytes).also { read = it } != -1) {
                    outputStream.write(bytes, 0, read)
                }
            }
        } catch (e: IOException) {
            Log.e("Failed to load file: ", e.message.toString())
        }
    }
}
