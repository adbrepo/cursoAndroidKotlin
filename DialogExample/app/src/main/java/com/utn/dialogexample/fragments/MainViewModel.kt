package com.utn.dialogexample.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    var input : MutableLiveData<String> = MutableLiveData<String>()
}
