package com.manadigital.recyclerview1.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.manadigital.recyclerview1.R
import com.manadigital.recyclerview1.entities.Mascota

class NewItem : Fragment() {

    lateinit var v: View

    var listMascotas : MutableList<Mascota> = ArrayList()


    companion object {
        fun newInstance() = NewItem()
    }

    private lateinit var viewModel: NewItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.new_item_fragment, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        listMascotas = NewItemArgs.fromBundle(arguments!!).listMascotas!!.toMutableList()
        listMascotas.add(Mascota("David",Mascota.Constants.typeGato,"Arlequin",8,"web"))
        val directions = NewItemDirections.actionNewItemToListFragment(listMascotas.toTypedArray())
        v.findNavController().navigate(directions)



    }
}
