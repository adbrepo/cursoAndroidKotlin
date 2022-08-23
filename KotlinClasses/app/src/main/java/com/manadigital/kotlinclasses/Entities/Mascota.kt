package com.manadigital.kotlinclasses.Entities

class Mascota (nombre : String , tipo : String , raza : String, edad :Int)  {

     var nombre : String
     var tipo : String
     var raza : String
     var edad : Int

    init {
        this.nombre = nombre
        this.tipo = tipo
        this.raza = raza
        this.edad = edad
    }

    fun  CalcularEdad (edad : Int) : Int {
        val edadnueva : Int
        edadnueva = edad*7
        return edadnueva
    }

    class Constants {
        companion object {
            val typeGato = "GATO"
            val typePerro = "PERRO"
        }
    }

    override fun toString(): String {
        return "Mascota(nombre='$nombre', tipo='$tipo', raza='$raza', edad=$edad)"
    }
}