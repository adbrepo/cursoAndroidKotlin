package com.manadigital.kotlinclasses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manadigital.kotlinclasses.Entities.Mascota

class MainActivity : AppCompatActivity() {

    lateinit var miMascota: Mascota

    var edadCalculada : Int = 0

    var mascotas: MutableList<Mascota> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        miMascota = Mascota("Raul","PERRO","Calle",3)  //Instanciar objeto mascota

        Log.d("TEST",miMascota.nombre + " " + miMascota.edad)

        miMascota.edad = 4
        miMascota.nombre = "Jorge"

        Log.d("TEST",miMascota.nombre + " " + miMascota.edad)

        edadCalculada = miMascota.CalcularEdad(miMascota.edad)

        Log.d("TEST",edadCalculada.toString())

        val mascota1 = Mascota("Pedro",Mascota.Constants.typePerro,"Colie",3)

        mascotas.add(mascota1)


        mascotas.add(Mascota("Rodolfo",Mascota.Constants.typePerro,"Fox Terrier",4))
        mascotas.add(Mascota("Emilio",Mascota.Constants.typePerro,"Gran Danes",5))
        mascotas.add(Mascota("Luis",Mascota.Constants.typeGato,"Siames",6))
        mascotas.add(Mascota("Carlos",Mascota.Constants.typeGato,"Pardo",7))
        mascotas.add(Mascota("David",Mascota.Constants.typeGato,"Arlequin",8))
//
        val mascota = Mascota("David",Mascota.Constants.typeGato,"Arlequin",8)

        Log.d("TEST",mascota.toString())


        for (mascotaActual in mascotas) {
            Log.d("TEST",mascotaActual.nombre+" " + mascotaActual.tipo +" " + mascotaActual.raza+ " " +mascotaActual.edad+ "\n")
        }

        mascotas[0].nombre="NombreNuevo"

        mascotas.forEach {
            Log.d("TEST",it.nombre+" " + it.tipo +" " + it.raza+ " " +it.edad+ "\n")
        }

        Log.d("TEST",mascotas[0].nombre)

    }
}
