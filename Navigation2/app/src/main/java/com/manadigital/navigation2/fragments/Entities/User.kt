package com.manadigital.navigation2.fragments.Entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class User(email: String, password: String) : Parcelable {
    var email: String

    var password: String

    init {

        this.email = email
        this.password = password
    }
}