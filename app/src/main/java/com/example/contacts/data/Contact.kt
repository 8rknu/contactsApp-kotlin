package com.example.contacts.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity("contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val surname : String,
    val phoneNumber : String
):Parcelable
