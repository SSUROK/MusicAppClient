package ru.surok.clientserverappproject.views.elements

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.surok.clientserverappproject.R
import ru.surok.clientserverappproject.databinding.FragmentListElementSongBigBinding
//import ru.surok.clientserverappproject.views.viewModels.SongElementViewModel

class RecyclerSongElementFragment : Fragment() {

//    private lateinit var binding: FragmentListElementSongBigBinding
//    private val viewmodel: SongElementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentListElementSongBigBinding.inflate(inflater, container, false)
//        viewmodel.getCover(1)
//        viewmodel.img?.observe(viewLifecycleOwner) {img ->
//            val bitmap = BitmapFactory.decodeByteArray(img, 0, img.size)
//            binding.coverImageSongElem.setImageBitmap(bitmap)
//        }
//        return binding.root
        return inflater.inflate(
            R.layout.fragment_list_element_history,
            container,
            false
        )
    }
}