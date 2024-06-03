package ru.surok.clientserverappproject.views.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.surok.clientserverappproject.R
import ru.surok.clientserverappproject.databinding.FragmentNoInternetConnectionBinding

class NoInternetConnectionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoInternetConnectionBinding.inflate(inflater, container, false)
//        binding.reloadBtn.setOnClickListener{
//            searchFreeSound(binding.editTextText.text.toString())
//        }
        return inflater.inflate(R.layout.fragment_no_internet_connection, container, false)
    }


}