package ru.surok.clientserverappproject.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.surok.clientserverappproject.R
import ru.surok.clientserverappproject.data.models.FreeSoundSound
import java.io.InputStream
import java.net.URL


class FreeSoundRecyclerAdapter(private val data: List<FreeSoundSound?>?,
                               private val onClickListener: ListOnClickListener,):
    RecyclerView.Adapter<FreeSoundRecyclerAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coverImage: ImageView =
            view.findViewById(R.id.coverImageSongElem)
        val author: TextView =
            view.findViewById(R.id.bandNameSongElem)
        val songName: TextView =
            view.findViewById(R.id.songNameSongElem)
        val addButton: ImageButton =
            view.findViewById(R.id.addBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_element_song_big,
                parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:
    Int) {
        val item = data?.get(position)
        runBlocking {
            launch(Dispatchers.IO) {
                val inps = URL(item?.images?.get("waveform_m")).content as InputStream
                val d = Drawable.createFromStream(inps, "src name")
                holder.coverImage.setImageDrawable(d)
            }
        }

        holder.author.text = item?.username
        holder.songName.text = item?.name
        holder.addButton.visibility = View.VISIBLE
        holder.addButton.setOnClickListener{
            if (item != null) {
                onClickListener.onClick(it, item)
            }
        }
    }


    override fun getItemCount() = data!!.size
}