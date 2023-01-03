
package com.example.carplanner.ui.oc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OcViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Ubezpieczenia"
    }
    val text: LiveData<String> = _text
}