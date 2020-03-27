package com.manadigital.kotlinclasses.Entities

class Mascota (nombre : String, tipo : String , raza : String, edad :Int) {

   private var nombre : String
    var tipo : String
    var raza : String
    var edad : Int


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

    fun setNombre (nombre : String){

        this.nombre = nombre
    }

    fun getNombre () : String {

        return(this.nombre)
    }




}