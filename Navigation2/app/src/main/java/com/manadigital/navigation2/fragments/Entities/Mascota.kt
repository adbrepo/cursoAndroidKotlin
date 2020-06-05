package com.manadigital.kotlinclasses.Entities

import android.os.Parcel
import android.os.Parcelable

class Mascota(nombre: String, tipo: String, raza: String, edad: Int) : Parcelable {
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

    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(nombre)
        writeString(tipo)
        writeString(raza)
        writeInt(edad)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Mascota> = object : Parcelable.Creator<Mascota> {
            override fun createFromParcel(source: Parcel): Mascota = Mascota(source)
            override fun newArray(size: Int): Array<Mascota?> = arrayOfNulls(size)
        }
    }
}