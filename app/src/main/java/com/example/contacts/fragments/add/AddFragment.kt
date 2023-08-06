package com.example.contacts.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contacts.R
import com.example.contacts.data.Contact
import com.example.contacts.data.ContactViewModel
import com.example.contacts.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding : FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var  mContactViewModel : ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater,container,false)

        mContactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binding.addButton.setOnClickListener {
            addContact()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun addContact() {
        val name = binding.addName.text.toString()
        val surname = binding.addSurname.text.toString()
        val phoneNumber = binding.addPhone.text.toString()

        if (inputCheck(name,surname,phoneNumber)){
            val contact = Contact(0,name,surname,phoneNumber)
            mContactViewModel.AddContact(contact)
            Toast.makeText(requireContext(),"Contact added successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please fill all fields!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, surname: String, phoneNumber: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(phoneNumber))
    }

}