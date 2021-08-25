package com.manadigital.navigation1.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manadigital.navigation1.R

class EjemploFragment : Fragment() {

    companion object {
        fun newInstance() = EjemploFragment()
    }

    private lateinit var viewModel: EjemploViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ejemplo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EjemploViewModel::class.java)
        // TODO: Use the ViewModel
    }

}