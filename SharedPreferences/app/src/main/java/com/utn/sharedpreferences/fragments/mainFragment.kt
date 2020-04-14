package com.utn.sharedpreferences.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



import com.utn.sharedpreferences.R


class mainFragment : Fragment() {

    lateinit var v : View

    private val PREF_NAME = "myPreferences"

    var user : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_main, container, false)

        return v
    }

    override fun onStart() {
        super.onStart()
        val sharedPref: SharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val editor = sharedPref.edit()
        editor.putString("USER", "Ale")
        editor.apply()

        user = sharedPref.getString("USER","default")!!

        Log.d("TAG",user)

    }
}
