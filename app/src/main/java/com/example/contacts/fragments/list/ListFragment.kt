package com.example.contacts.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.R
import com.example.contacts.data.ContactViewModel
import com.example.contacts.databinding.FragmentListBinding

class ListFragment : Fragment(),MenuProvider {

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContactViewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentListBinding.inflate(inflater,container,false)

        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mContactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        mContactViewModel.readAllContacts.observe(viewLifecycleOwner, Observer { contact ->
            adapter.setData(contact)
        })


        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.delte_menu){
            deleteAllContacts()
        }

        return onOptionsItemSelected(menuItem)
    }

    private fun deleteAllContacts() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_ ->
            mContactViewModel.deleteAllContacts()
            Toast.makeText(requireContext(),"All contacts are deleted successfully", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete All")
        builder.setMessage("Are you sure you want to delete all contacts?")
        builder.create().show()
    }
}