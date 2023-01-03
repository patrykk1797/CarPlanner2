package com.example.carplanner.ui.naprawa
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carplanner.R


class NaprawaFragment : Fragment() {

    private lateinit var naprawaViewModel: NaprawaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        naprawaViewModel =
            ViewModelProvider(this).get(NaprawaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_naprawa, container, false)
        val textView: TextView = root.findViewById(R.id.text_naprawa)
        naprawaViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
