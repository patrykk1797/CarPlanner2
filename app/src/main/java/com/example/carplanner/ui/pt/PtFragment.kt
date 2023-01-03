package com.example.carplanner.ui.pt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carplanner.R


class PtFragment : Fragment() {
    private lateinit var ptViewModel: PtViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ptViewModel =
            ViewModelProvider(this).get(PtViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pt, container, false)
        val textView: TextView = root.findViewById(R.id.text_pt)
        ptViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
