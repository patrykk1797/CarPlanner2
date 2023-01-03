package com.example.carplanner.ui.pt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PtViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Przegląd techniczny"
    }
    val text: LiveData<String> = _text
}
