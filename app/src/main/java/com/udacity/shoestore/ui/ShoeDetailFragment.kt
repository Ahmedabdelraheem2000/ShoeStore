package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ViewModel_ShoeList

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ViewModel_ShoeList by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentShoeDetailBinding.inflate(layoutInflater,container,false)

        viewModel.resetNewShoe()
        binding.viewModel = viewModel
        // save button
        binding.saveButton.setOnClickListener {

            if (viewModel.addNewShoe()) {

                it.findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
                )

            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.errorlabel),
                    Snackbar.LENGTH_LONG)
                    .show()
            }
        }
        return binding.root
    }

}