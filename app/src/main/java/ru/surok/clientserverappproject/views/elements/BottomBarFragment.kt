package ru.surok.clientserverappproject.views.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.surok.clientserverappproject.App
import ru.surok.clientserverappproject.databinding.FragmentBottomBarBinding

class BottomBarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding = FragmentBottomBarBinding.inflate(inflater, container, false)
//        binding.themeSwitcher.isChecked = (context?.applicationContext as App).darkTheme
//        binding.themeSwitcher.setOnCheckedChangeListener { switcher, checked ->
//            (context?.applicationContext as App).switchTheme(checked)
//        }
        return binding.root
    }
}