package com.manadigital.kotlinclasses.Entities

class Cuadrado (var lado : Double, nombre: String, perimetro: Double, superficie: Double) :
    Figura(nombre, perimetro, superficie) {

    override fun calcularPerimetro(): Double {
        TODO("Not yet implemented")
    }

    override fun calcularSuperficie(): Double {
        TODO("Not yet implemented")
    }
}