package com.example.contacts.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.data.Contact
import com.example.contacts.databinding.RecyclerRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ContactViewHolder>() {

    private var contactList = emptyList<Contact>()

    inner class ContactViewHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        with(holder){
            with(contactList[position]){
                binding.nameTextView.text = this.name
                binding.surnameTextView.text = this.surname
                binding.phoneTextView.text = this.phoneNumber

                binding.recyclerRow.setOnClickListener{
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(contactList[position])
                    holder.itemView.findNavController().navigate(action)
                }
            }
        }
    }

    fun setData(contact : List<Contact>){
        this.contactList = contact
        notifyDataSetChanged()
    }
}