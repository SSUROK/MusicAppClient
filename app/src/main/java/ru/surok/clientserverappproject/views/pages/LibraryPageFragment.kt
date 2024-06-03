package ru.surok.clientserverappproject.views.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.surok.clientserverappproject.adapters.FreeSoundRecyclerAdapter
import ru.surok.clientserverappproject.adapters.LibraryAdapter
import ru.surok.clientserverappproject.databinding.FragmentLibraryPageBinding
import ru.surok.clientserverappproject.views.viewModels.LibraryPageViewModel


class LibraryPageFragment : Fragment() {
    private lateinit var binding: FragmentLibraryPageBinding
    private val viewModel: LibraryPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryPageBinding.inflate(inflater, container, false)
        viewModel.getLibraryFromRepo()
        val adapter = LibraryAdapter()
        val recyclerView: RecyclerView = binding.albums
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.library.observe(viewLifecycleOwner) { library ->

            if (library.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
                binding.noActiveAccount.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                binding.noActiveAccount.visibility = View.GONE
                (binding.albums.adapter as LibraryAdapter).updateData(library)
            }
        }
        return binding.root
    }
}