package com.manadigital.userinterface1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var txtLabel : TextView
    lateinit var btnChangeText : Button

    var size : Float = 

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtLabel = findViewById(R.id.txtLabel)                 //Referencia de vistas al xml
        btnChangeText = findViewById(R.id.btnChangText)


        btnChangeText.setOnClickListener {

            txtLabel.text = "texto cambiado"
            txtLabel.textSize

    }
}
