package com.utn.dialogexample.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.utn.dialogexample.R

class MainFragment : Fragment() {

    lateinit var v : View

    lateinit var btnDialog : Button
    lateinit var txtLabel : TextView


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.main_fragment, container, false)

        btnDialog = v.findViewById(R.id.btn_dialog)
        txtLabel = v.findViewById(R.id.txt_label)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        btnDialog.setOnClickListener {

            val action = MainFragmentDirections.actionMainFragmentToDialogFragment()
            v.findNavController().navigate(action)

        }

        viewModel.input.observe(viewLifecycleOwner, Observer { result ->

            txtLabel.text = result.toString()
        })

    }
}
