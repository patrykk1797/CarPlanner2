package com.example.carplanner.ui.oc
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carplanner.R


class OcFragment : Fragment() {

    private lateinit var ocViewModel: OcViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ocViewModel =
            ViewModelProvider(this).get(OcViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_oc, container, false)
        val textView: TextView = root.findViewById(R.id.text_naprawa)
        ocViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }}
