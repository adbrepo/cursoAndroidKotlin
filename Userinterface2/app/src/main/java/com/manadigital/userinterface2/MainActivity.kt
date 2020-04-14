package com.manadigital.userinterface2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var txtCartel : TextView
    lateinit var edtInput : EditText
    lateinit var btnAccept : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCartel = findViewById(R.id.txt_label)
        edtInput = findViewById(R.id.edt_input)
        btnAccept = findViewById(R.id.btn_accept)

        btnAccept.setOnClickListener {

            if (edtInput.length()>0){
                txtCartel.text = "Texto ingresado: " + edtInput.text
            }
            else{
                Snackbar.make(root_layout, "Campo de datos vacio", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
