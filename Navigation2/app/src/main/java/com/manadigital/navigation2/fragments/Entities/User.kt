package com.manadigital.navigation2.fragments.Entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(var email: String, var password: String) : Parcelable {

}