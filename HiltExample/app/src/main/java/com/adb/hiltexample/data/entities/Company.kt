package com.adb.hiltexample.data.entities


class Company  (var name : String, var  employees : MutableList<Employee>) {

constructor() : this("", arrayListOf())

    override fun toString(): String {
        return "Company(name='$name', employee=$employees)"
    }
}