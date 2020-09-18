package com.utn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.utn.viewmodellivedataexample.R

class fragment1 : Fragment() {

    companion object {
        fun newInstance() = fragment1()
    }

    lateinit var v: View

    private lateinit var viewModel: Fragment1ViewModel
    lateinit var btnChange : Button
    lateinit var txtCartel : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment1_fragment, container, false)

        btnChange = v.findViewById(R.id.btn_go)
        txtCartel = v.findViewById(R.id.txt_cartel)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(Fragment1ViewModel::class.java)
        // TODO: Use the ViewModel


        viewModel.name.observe(viewLifecycleOwner, Observer { result ->

            txtCartel.text = result.toString()
        })
    }


    override fun onStart() {
        super.onStart()

        btnChange.setOnClickListener {

            viewModel.changeName()
        }
    }
}
