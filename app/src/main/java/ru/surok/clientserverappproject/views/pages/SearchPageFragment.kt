package ru.surok.clientserverappproject.views.pages

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.surok.clientserverappproject.R
import ru.surok.clientserverappproject.adapters.FreeSoundRecyclerAdapter
import ru.surok.clientserverappproject.adapters.HistoryRecyclerAdapter
import ru.surok.clientserverappproject.data.models.HistoryElement
import ru.surok.clientserverappproject.data.repos.FreeSoundRepo
import ru.surok.clientserverappproject.databinding.FragmentSearchPageBinding

class SearchPageFragment : Fragment() {

    private var searchString = ""
    private lateinit var binding : FragmentSearchPageBinding
    private lateinit var sharedPrefs : SharedPreferences
    private lateinit var mainThreadHandler : Handler
    private val searchRunnable = Runnable {searchFreeSound()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainThreadHandler = Handler(Looper.getMainLooper())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        sharedPrefs = requireActivity().getSharedPreferences(
            R.string.shared_pref_name_key.toString(),
            Context.MODE_PRIVATE
        )
        binding.editTextText.addTextChangedListener {
            binding.clearBtn.visibility = View.VISIBLE
            if (it != null) {
                if (it.isEmpty()) {
                    binding.clearBtn.visibility = View.GONE
                }
            }
        }

//        binding.editTextText.requestFocus()

        binding.editTextText.setOnFocusChangeListener { _, b ->
            if (b) {
                val h = getHistory()
                val adapter = HistoryRecyclerAdapter(h)
                val recyclerView: RecyclerView = binding.historyList
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(activity)
//                binding.historyCard.layoutParams.height = recyclerView.height
                if (h.isNotEmpty())
                    binding.historyCard.visibility = View.VISIBLE
            } else{
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.editTextText.windowToken, 0)
            }
        }

        binding.editTextText.doAfterTextChanged { searchDebounce(searchRunnable) }

        binding.editTextText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO){
                addHistory(v.text.toString())
                mainThreadHandler.removeCallbacks(searchRunnable)
                searchRunnable.run()
                v.clearFocus()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.clearBtn.setOnClickListener {_->
            binding.editTextText.text.clear()
//            binding.editTextText.requestFocus()
//            val imm = activity?.getSystemService(InputMethodManager::class.java)
//            imm?.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
        }

        binding.clearHistoryBtn.setOnClickListener {
            sharedPrefs.edit()?.putStringSet(R.string.history_preference.toString(), LinkedHashSet<String>(10))?.apply()
            binding.historyCard.visibility = View.GONE
        }

        binding.reloadBtn.setOnClickListener{
            searchFreeSound()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_STRING, searchString)
    }

    private fun getHistory(): List<HistoryElement> {
        val h = sharedPrefs.getStringSet(R.string.history_preference.toString(), LinkedHashSet<String>())
        val a = ArrayList<HistoryElement>(h!!.size)
        for (e in h){
            a.add(HistoryElement(e))
        }
        return a
    }

    private fun addHistory(search:String){
        if (search.isEmpty()) return
        val h = sharedPrefs.getStringSet(R.string.history_preference.toString(), LinkedHashSet<String>(10))
        val h1 = LinkedHashSet<String>(10)
        h1.add(search)
        for (i in h!!.size-1 downTo 0) {
            h1.add(h.elementAt(i))
        }
        if (h1.size > 10) h1.remove(h1.last())
        sharedPrefs.edit()?.putStringSet(R.string.history_preference.toString(), h1)?.apply()
    }

    private fun searchFreeSound(){
        val text = binding.editTextText.text.toString()
        if (text.isEmpty()) {return}
        binding.progressBar.visibility = View.VISIBLE
        binding.emptySearchCard.visibility = View.GONE
        binding.noInternetCard.visibility = View.GONE
        binding.historyCard.visibility = View.GONE
        val freeSoundService = FreeSoundRepo()
        freeSoundService.searchByText(text, "id,url,name,images,tags,username") { sounds ->
            binding.progressBar.visibility = View.GONE
            if (sounds == null) {
                binding.noInternetCard.visibility = View.VISIBLE
            } else {
                val adapter = FreeSoundRecyclerAdapter(sounds.results)
                val recyclerView: RecyclerView = binding.recyclerView
                if (sounds.count == 0) {
                    recyclerView.visibility = View.GONE
                    binding.emptySearchCard.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                }
            }
        }
    }

    private fun searchDebounce(searchRunnable: Runnable){
        mainThreadHandler.removeCallbacks(searchRunnable)
        mainThreadHandler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    companion object {
        const val SEARCH_STRING = "SEARCH_STRING"
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}