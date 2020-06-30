package com.manadigital.recyclerview1.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

import com.manadigital.recyclerview1.R
import com.manadigital.recyclerview1.entities.Mascota
import com.manadigital.recyclerview1.adapters.MascotaListAdapter

class listFragment : Fragment() {

    lateinit var v: View

    lateinit var recMascotas : RecyclerView

    var mascotas : MutableList<Mascota> = ArrayList<Mascota>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mascotasListAdapter: MascotaListAdapter

    companion object {
        fun newInstance() = listFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.list_fragment, container, false)
        recMascotas = v.findViewById(R.id.rec_mascotas)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        mascotas.add(Mascota("Pedro",Mascota.Constants.typePerro,"Colie",3))
        mascotas.add(Mascota("Rodolfo",Mascota.Constants.typePerro,"Fox Terrier",4))
        mascotas.add(Mascota("Emilio",Mascota.Constants.typePerro,"Gran Danes",5))
        mascotas.add(Mascota("Luis",Mascota.Constants.typeGato,"Siames",6))
        mascotas.add(Mascota("Carlos",Mascota.Constants.typeGato,"Pardo",7))
        mascotas.add(Mascota("David",Mascota.Constants.typeGato,"Arlequin",8))

        recMascotas.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(context)
        recMascotas.layoutManager = linearLayoutManager

        mascotasListAdapter = MascotaListAdapter(mascotas){position -> onItemClick(position)}
      //  mascotasListAdapter = MascotaListAdapter(mascotas)

        recMascotas.adapter = mascotasListAdapter
    }

     fun onItemClick ( position : Int ) {
        Snackbar.make(v,position.toString(),Snackbar.LENGTH_SHORT).show()
    }

}
