package com.utn.miprimeraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    lateinit var txtCartel : TextView

    var contenidoCartel : String = "hola Ale"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCartel = findViewById(R.id.txtCartel)

        txtCartel.text = contenidoCartel


    }


}