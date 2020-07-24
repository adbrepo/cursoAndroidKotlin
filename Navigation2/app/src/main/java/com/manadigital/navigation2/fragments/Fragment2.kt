package com.manadigital.navigation2.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.manadigital.kotlinclasses.Entities.Mascota

import com.manadigital.navigation2.R
import com.manadigital.navigation2.fragments.Entities.User

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {

    lateinit var v: View
    var mascotas : MutableList<Mascota> = ArrayList<Mascota>()
    lateinit var user : User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_fragment2, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()

        var mascotaName  = Fragment2Args.fromBundle(requireArguments()).mascotaName
        var cantidad = Fragment2Args.fromBundle(requireArguments()).cantidad
        mascotas = Fragment2Args.fromBundle(requireArguments()).listaMascotas.toMutableList()
        user = Fragment2Args.fromBundle(requireArguments()).user

        Snackbar.make(v,mascotaName,Snackbar.LENGTH_SHORT).show()




        for (mascotaActual in mascotas){

            Log.d("Test",mascotaActual.nombre+" " + mascotaActual.tipo +" " + mascotaActual.raza+ " "
                    +mascotaActual.edad+ "\n")
        }


    }
}
