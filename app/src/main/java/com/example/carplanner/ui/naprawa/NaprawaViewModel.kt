package com.example.carplanner.ui.naprawa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NaprawaViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Naprawy"
    }
    val text: LiveData<String> = _text
}
