package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ViewModel_ShoeList : ViewModel() {
    private val Mutable_shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = Mutable_shoeList
    var Shoe_Name = ""
    var Shoe_Size = ""
    var Company_Name = ""
    var Shoe_Description = ""
    init {
        Mutable_shoeList.value = mutableListOf()
    }
    private fun addShoe(name: String, size: Double, company: String, description: String) {
        val shoe = Shoe(name, size, company, description)
        Mutable_shoeList.value!!.add(shoe)
    }
    fun check_details(): Boolean {

        if (!checkinputdetails()) return false

        addShoe(
            Shoe_Name,
            Shoe_Size.toDouble(),
            Company_Name,
            Shoe_Description
        )

        return true
    }
    fun resetNewShoe() {
         Shoe_Name = ""
         Shoe_Size = ""
         Company_Name = ""
         Shoe_Description = ""
    }
    fun addNewShoe(): Boolean {

        if (!checkinputdetails()) return false

        addShoe(
            Shoe_Name,
            Shoe_Size.toDouble(),
            Company_Name,
            Shoe_Description
        )

        return true
    }
    private fun checkinputdetails(): Boolean {

        if (Shoe_Name.isBlank() ||
            Shoe_Size.isBlank() ||
            Company_Name.isBlank()  ||
            Shoe_Description.isBlank()) {
            return false
        }

        return true
    }
}