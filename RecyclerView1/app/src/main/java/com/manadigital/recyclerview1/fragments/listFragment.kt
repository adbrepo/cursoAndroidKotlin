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

    lateinit var btnAdd : FloatingActionButton

    lateinit var recMascotas : RecyclerView

    var listMascotas : MutableList<Mascota>? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mascotasListAdapter: MascotaListAdapter




    companion object {
        fun newInstance() = listFragment()
    }

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.list_fragment, container, false)
        btnAdd = v.findViewById(R.id.btn_add)
        recMascotas = v.findViewById(R.id.rec_mascotas)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        btnAdd.setOnClickListener {

            var action = listFragmentDirections.actionListFragmentToNewItem(listMascotas?.toTypedArray())
            v.findNavController().navigate(action)
        }

        listMascotas =listFragmentArgs.fromBundle(arguments!!).newItem?.toMutableList()
        if(listMascotas == null){
            listMascotas = ArrayList<Mascota>()
        }
        Log.d("","")

        recMascotas.setHasFixedSize(true)


        linearLayoutManager = LinearLayoutManager(context)
        recMascotas.layoutManager = linearLayoutManager

        mascotasListAdapter = MascotaListAdapter(listMascotas!!){onItemClick()}
        recMascotas.adapter = mascotasListAdapter


    }

    public fun onItemClick (){
        Snackbar.make(v,"click",Snackbar.LENGTH_SHORT).show()
    }

}
