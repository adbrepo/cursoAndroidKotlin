package com.ort.tp3test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var nombre : String
    val edad : Int = 20

    lateinit var txtCartel : TextView
    lateinit var btnShow : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCartel = findViewById(R.id.txtCartel)
        btnShow = findViewById(R.id.btnShow)
        btnShow.setOnClickListener {
            escribirCartel(" Hola ale")
        }
    }

    fun escribirCartel (texto : String){

        txtCartel.text = texto
    }

    fun nombreDeFuncion () : String {
        return "hola"
    }

}