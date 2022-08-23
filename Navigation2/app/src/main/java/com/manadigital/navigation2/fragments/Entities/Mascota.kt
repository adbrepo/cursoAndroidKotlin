package com.manadigital.navigation2.fragments.Entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Mascota(var nombre: String, var tipo: String, var raza: String, var edad: Int) : Parcelable {

    class Constants {
        companion object {
            val typeGato = "GATO"
            val typePerro = "PERRO"
        }
    }

    fun CalcularEdad(edad: Int): Int {

        var edadnueva: Int
        edadnueva = edad * 7
        return edadnueva
    }


}