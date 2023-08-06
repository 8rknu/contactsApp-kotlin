package com.example.contacts.data

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {

    val readAllContacts : LiveData<List<Contact>> = contactDao.readAllContact()

    suspend fun AddContact(contact: Contact){
        contactDao.AddContact(contact)
    }

    suspend fun UpdateContact(contact: Contact){
        contactDao.UpdateContact(contact)
    }

    suspend fun DeleteContact(contact: Contact){
        contactDao.DeleteContact(contact)
    }

    suspend fun DeleteAllContacts(){
        contactDao.DeleteAllContacts()
    }
}