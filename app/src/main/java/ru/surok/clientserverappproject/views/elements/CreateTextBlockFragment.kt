package ru.surok.clientserverappproject.views.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.surok.clientserverappproject.R

/**
 * A simple [Fragment] subclass.
 * Use the [CreateTextBlockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTextBlockFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_text_block, container, false)
    }
}