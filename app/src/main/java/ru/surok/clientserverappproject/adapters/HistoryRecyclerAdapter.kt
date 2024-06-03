package ru.surok.clientserverappproject.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.surok.clientserverappproject.R
import ru.surok.clientserverappproject.data.models.FreeSoundSound
import ru.surok.clientserverappproject.data.models.HistoryElement
import java.io.InputStream
import java.net.URL


class HistoryRecyclerAdapter(private val data: List<HistoryElement?>?): RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyText: TextView =
            view.findViewById(R.id.historyText)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_element_history,
                parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position:
    Int) {
        val item = data?.get(position)

        holder.historyText.text = item?.text
    }
    override fun getItemCount() = data!!.size
}