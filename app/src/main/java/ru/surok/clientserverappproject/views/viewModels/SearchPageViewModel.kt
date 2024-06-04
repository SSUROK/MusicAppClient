package ru.surok.clientserverappproject.views.viewModels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ru.surok.clientserverappproject.data.models.FreeSoundSound
import ru.surok.clientserverappproject.data.models.MusicRequest
import ru.surok.clientserverappproject.data.repos.ServerRepo
import java.io.ByteArrayOutputStream


class SearchPageViewModel(application: Application): AndroidViewModel(application) {

    private val serverRepo = ServerRepo()

    fun addSound(sound: FreeSoundSound, cover: Drawable? = null){
    // name, duration, images(waveform_m), view.cover, username
        if (cover!= null) {
            val bitmap = (cover as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val bitmapdata = stream.toByteArray()
        }
        val request = MusicRequest(
            title = sound.name,
            duration = sound.duration,
//            cover = bitmapdata,
            artist = sound.username,
            album = "FreeSound",
            filePath = ""
        )
        serverRepo.addMusic(request) {
            if (it!= null){
                val toast = Toast.makeText(getApplication(), "Added", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}