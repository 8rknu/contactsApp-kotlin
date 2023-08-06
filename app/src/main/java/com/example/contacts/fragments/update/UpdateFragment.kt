package com.example.contacts.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contacts.R
import com.example.contacts.data.Contact
import com.example.contacts.data.ContactViewModel
import com.example.contacts.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment(),MenuProvider {

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding : FragmentUpdateBinding? = null
    private val binging get() = _binding!!

    private lateinit var mContactViewModel : ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)

        mContactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binging.updateName.setText(args.currentContact.name)
        binging.updateSurname.setText(args.currentContact.surname)
        binging.updatePhone.setText(args.currentContact.phoneNumber)

        binging.updateButton.setOnClickListener {
            updateContact()
        }

        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        // Inflate the layout for this fragment
        return binging.root
    }

    private fun updateContact() {
        val name = binging.updateName.text.toString()
        val surname = binging.updateSurname.text.toString()
        val phoneNumber = binging.updatePhone.text.toString()

        if(inputCheck(name,surname,phoneNumber)){
            val updateContact = Contact(args.currentContact.id,name,surname,phoneNumber)
            mContactViewModel.UpdateContact(updateContact)
            Toast.makeText(requireContext(),"Contact updated successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        else{
            Toast.makeText(requireContext(),"Please fill all fields!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, surname: String, phoneNumber: String): Boolean {

        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(phoneNumber))

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

        menuInflater.inflate(R.menu.delete_menu,menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.delte_menu){

            deleteContact()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun deleteContact() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_ ->
            mContactViewModel.deleteContact(args.currentContact)
            Toast.makeText(requireContext(),"Contact deleted successfully", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete Contact")
        builder.setMessage("Are you sure you want to delete ${args.currentContact.name}")
        builder.create().show()
    }

}