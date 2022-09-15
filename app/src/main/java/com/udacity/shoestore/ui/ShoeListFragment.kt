package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodel.ViewModel_ShoeList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_itemlist.view.*


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ViewModel_ShoeList by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.supportFragmentManager.popBackStack(R.id.shoeListFragment,R.id.loginFragment)

        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel.shoeList.observe(viewLifecycleOwner) {
            binding.shoeList.removeAllViews()
            for (shoe in it) {
                addShoe(shoe.name,shoe.size,shoe.company)
            }
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_details, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.logout -> {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
        return super.onOptionsItemSelected(item)
    }
    private fun addShoe( name: String,  size: Double,  company: String) {
        val view = layoutInflater.inflate(R.layout.card_itemlist, null)
        view.name.text = name
        view.company.text = size.toString()
        view.size.text = company
        binding.shoeList.addView(view)
    }
}