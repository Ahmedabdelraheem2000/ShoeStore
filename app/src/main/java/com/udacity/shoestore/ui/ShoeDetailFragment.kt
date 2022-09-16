package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ViewModel_ShoeList

class ShoeDetailFragment : Fragment() {
    private val viewModel: ViewModel_ShoeList by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_instructions, container, false)

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
        // Cancel Button
        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment2)
        }
        return binding.root
    }

}