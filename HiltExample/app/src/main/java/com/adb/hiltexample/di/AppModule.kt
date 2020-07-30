package com.adb.hiltexample.di

import com.adb.hiltexample.data.entities.Company
import com.adb.hiltexample.data.entities.Employee
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn (ApplicationComponent::class)
object  AppModule {
    @Provides
    fun provideEmployee(): Employee = Employee("")

    @Provides
    fun provideEmployees(): MutableList<Employee> = mutableListOf()

    @Provides
    fun provideCompany(employees: MutableList<Employee>): Company = Company("",employees)



}