package com.manadigital.kotlinclasses.Entities

import android.os.Parcel
import android.os.Parcelable

class Mascota(nombre: String, tipo: String, raza: String, edad: Int)  {
    var nombre: String

    var tipo: String

    var raza: String

    var edad: Int

    class Constants {
        companion object {
            val typeGato = "GATO"
            val typePerro = "PERRO"
        }
    }

    init {
        this.nombre = nombre
        this.tipo = tipo
        this.raza = raza
        this.edad = edad
    }

    fun CalcularEdad(edad: Int): Int {

        var edadnueva: Int
        edadnueva = edad * 7
        return edadnueva
    }


}