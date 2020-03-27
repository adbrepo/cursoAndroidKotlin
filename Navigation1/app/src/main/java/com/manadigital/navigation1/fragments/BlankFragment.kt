package com.manadigital.navigation1.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.manadigital.navigation1.R

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {

    lateinit var v : View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_blank, container, false)


        return v
    }


}
