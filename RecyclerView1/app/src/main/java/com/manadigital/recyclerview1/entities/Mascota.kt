package com.manadigital.recyclerview1.entities

import android.os.Parcel
import android.os.Parcelable


class Mascota(nombre: String?, tipo: String?, raza: String?, edad: Int?) {
    var nombre: String = ""

    var tipo: String = ""

    var raza: String = ""

    var edad: Int = 0

    var urlImage: String = ""

    class Constants {
        companion object {
            val typeGato = "GATO"
            val typePerro = "PERRO"
        }
    }

    init {
        this.nombre = nombre!!
        this.tipo = tipo!!
        this.raza = raza!!
        this.edad = edad!!
        this.urlImage = urlImage!!
    }


}
