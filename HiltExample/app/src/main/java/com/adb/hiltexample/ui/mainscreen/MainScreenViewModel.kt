package com.adb.hiltexample.ui.mainscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.adb.hiltexample.data.entities.Company
import com.adb.hiltexample.data.entities.Employee
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class MainScreenViewModel @ViewModelInject constructor(var company : Company) : ViewModel() {
    // TODO: Implement the ViewModel

    fun  showCompany () : String {
        //company = Company(" ", Employee(""))
        company.name = "Mana"
    //    company.employee!!.name = "Ale"
       return company.toString()
   }

    fun uploadFirebase (){
        val db = Firebase.firestore

        db.collection("Test").document("test").set(company)
    }
}