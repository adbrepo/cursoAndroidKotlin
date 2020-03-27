package com.manadigital.navigation2.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

import com.manadigital.navigation2.R

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {

    lateinit var v: View

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

        val userName = Fragment2Args.fromBundle(arguments!!).userName
        Snackbar.make(v,userName,Snackbar.LENGTH_SHORT).show()

    }
}
