package com.utn.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Fragment1ViewModel : ViewModel() {


    val name = MutableLiveData<String>()

    var viewState : MutableLiveData<String> = MutableLiveData()

    lateinit var texto : String

    fun changeName (){
        name.value = "pepe"
    }

    fun changeText (text : String){
        texto = text
    }

    fun submitForm(){
        viewState.value = "loading"
        val result  =  validarFormulario()
        viewState.value = "idle"

    }


}
