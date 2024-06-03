package ru.surok.clientserverappproject.views.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.surok.clientserverappproject.R

/**
 * A simple [Fragment] subclass.
 * Use the [EmptySearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmptySearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty_search, container, false)
    }
}