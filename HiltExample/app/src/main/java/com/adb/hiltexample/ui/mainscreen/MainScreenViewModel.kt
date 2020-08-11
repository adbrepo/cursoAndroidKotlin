package com.adb.hiltexample.ui.mainscreen

import android.content.ContentValues
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.adb.hiltexample.data.entities.Company
import com.adb.hiltexample.data.entities.Employee
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject



class MainScreenViewModel @ViewModelInject constructor(var company : Company) : ViewModel() {
    // TODO: Implement the ViewModel

    suspend fun showCompany(): String {
        return getCompaniesByNameFirestore("Mana").toString()
    }

    fun uploadFirebase() {
        val db = Firebase.firestore

        company.employees.add(Employee("ale"))
        company.employees.add(Employee("juan"))
        company.employees.add(Employee("carlos"))
        db.collection("Test").document("test").set(company)
    }

    suspend fun getCompaniesByNameFirestore(name: String): Company {
        val db = Firebase.firestore

        val questionRef = db.collection("Test")
        val query = questionRef
                    try {
            val data = query
                .get()
                .await()
            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                company = document.toObject<Company>()
            }
        } catch (e: Exception) {
                        Log.d("Test",e.toString())
        }
        return company
    }
}