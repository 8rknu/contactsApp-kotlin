package com.example.contacts.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AddContact(contact: Contact)

    @Update
    suspend fun UpdateContact(contact: Contact)

    @Delete
    suspend fun DeleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    fun readAllContact() : LiveData<List<Contact>>

    @Query("DELETE FROM contact_table")
    suspend fun DeleteAllContacts()
}