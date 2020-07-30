package com.adb.hiltexample.data.entities

import javax.inject.Inject

class Company  (var name : String, var employee : Employee) {


    override fun toString(): String {
        return "Company(name='$name', employee=$employee)"
    }
}