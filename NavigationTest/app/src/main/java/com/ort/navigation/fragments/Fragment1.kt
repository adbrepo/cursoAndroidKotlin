package com.ort.navigation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.ort.navigation.R

class Fragment1 : Fragment() {
    lateinit var v : View

    lateinit var txtTile : TextView
    lateinit var btnNav : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_fragment1, container, false)

        txtTile = v.findViewById(R.id.txtTitle)
        btnNav = v.findViewById(R.id.btnNav)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnNav.setOnClickListener {

            val action = Fragment1Directions.actionFragment1ToFragment2()
            findNavController().navigate(action)

        }


    }





}