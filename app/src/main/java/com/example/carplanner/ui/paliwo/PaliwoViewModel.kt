package com.example.carplanner.ui.paliwo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaliwoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Tankowanie pojazdu"
    }
    val text: LiveData<String> = _text
}