package com.manadigital.recyclerview1.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.manadigital.recyclerview1.R

class DetailItem : Fragment() {

    lateinit var v: View


    companion object {
        fun newInstance() = DetailItem()
    }

    private lateinit var viewModel: DetailItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.detail_item_fragment, container, false)

        return v    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
