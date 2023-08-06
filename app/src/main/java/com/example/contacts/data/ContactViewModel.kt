package com.example.contacts.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    val readAllContacts:LiveData<List<Contact>>
    val repository : ContactRepository

    init {
        val contactDao = ContactDatabase.getAllDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        readAllContacts = repository.readAllContacts

    }

    fun AddContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.AddContact(contact)
        }
    }

    fun UpdateContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.UpdateContact(contact)
        }
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.DeleteContact(contact)
        }
    }

    fun deleteAllContacts(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.DeleteAllContacts()
        }
    }
}