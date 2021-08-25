package com.manadigital.navigation2.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.manadigital.kotlinclasses.Entities.Mascota

import com.manadigital.navigation2.R
import com.manadigital.navigation2.fragments.Entities.User

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    lateinit var v: View
    lateinit var btnGoToFragment2: Button
    lateinit var valor : String
    lateinit var user : User

    var mascotas : MutableList<Mascota> = ArrayList<Mascota>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_fragment1, container, false)

        btnGoToFragment2 = v.findViewById(R.id.btn_go_to_fragment2)
        // Inflate the layout for this fragment
        return v
    }


    override fun onStart() {
        super.onStart()

        user = User ("ale@email.com","123456")

        mascotas.add(Mascota("Pedro",Mascota.Constants.typePerro,"Colie",3))
        mascotas.add(Mascota("Rodolgo",Mascota.Constants.typePerro,"Fox Terrier",4))
        mascotas.add(Mascota("Emilio",Mascota.Constants.typePerro,"Gran Danes",5))
        mascotas.add(Mascota("Luis",Mascota.Constants.typeGato,"Siames",6))
        mascotas.add(Mascota("Carlos",Mascota.Constants.typeGato,"Pardo",7))
        mascotas.add(Mascota("David",Mascota.Constants.typeGato,"Arlequin",8))


        btnGoToFragment2.setOnClickListener {

            valor = "ALEEEE"
    val action = Fragment1Directions.actionFragment1ToFragment2(valor,20,mascotas.toTypedArray(),user)
            v.findNavController().navigate(action)


        }
    }
}
