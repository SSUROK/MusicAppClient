package ru.surok.clientserverappproject.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.surok.clientserverappproject.R
import ru.surok.clientserverappproject.data.models.MusicSample

class LibraryAdapter: RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    private var data = mutableListOf<MusicSample>()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coverImage: ImageView =
            view.findViewById(R.id.coverImageSongElem)
        val author: TextView =
            view.findViewById(R.id.bandNameSongElem)
        val songName: TextView =
            view.findViewById(R.id.songNameSongElem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_list_element_song_big,
                parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:
    Int) {
        val item = data[position]
        val bitmap = BitmapFactory.decodeByteArray(item.coverStream, 0, item.coverStream!!.size)
        holder.coverImage.setImageBitmap(bitmap)
        holder.author.text = item.artist
        holder.songName.text = item.title
    }
    override fun getItemCount() = data.size

    fun updateData(items: List<MusicSample>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}