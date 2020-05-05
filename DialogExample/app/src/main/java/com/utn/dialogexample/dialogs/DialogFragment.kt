package com.utn.dialogexample.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.nikartm.button.FitButton
import com.google.android.material.snackbar.Snackbar

import com.utn.dialogexample.R
import com.utn.dialogexample.fragments.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DialogFragment :  DialogFragment() {


    lateinit var v: View

    lateinit var edtCode: EditText
    lateinit var btnAccept: FitButton
    lateinit var btnCancel: FitButton

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dialog, container, false)

        edtCode = v.findViewById(R.id.edt_code_dialog)
        btnAccept = v.findViewById(R.id.btn_acept_dialog)
        btnCancel = v.findViewById(R.id.btn_cancel_dialog)
        return v
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onStart() {
        super.onStart()

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        btnCancel.setOnClickListener() {
            dismiss()
        }

        btnAccept.setOnClickListener {
            if (edtCode.length() > 0) {
                mainViewModel.input.value = edtCode.text.toString()
                dismiss()
            } else {
                Snackbar.make(
                    v, "campo vacio", Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}



