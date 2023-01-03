package com.example.carplanner.ui.paliwo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carplanner.R

class PaliwoFragment : Fragment() {

    private lateinit var paliwoViewModel: PaliwoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        paliwoViewModel =
                ViewModelProvider(this).get(PaliwoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_paliwo, container, false)
        val textView: TextView = root.findViewById(R.id.text_paliwo)
        paliwoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}