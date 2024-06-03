package ru.surok.clientserverappproject.views.viewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.surok.clientserverappproject.data.models.Music
import ru.surok.clientserverappproject.data.models.MusicDTO
import ru.surok.clientserverappproject.data.repos.ServerRepo
import java.io.InputStream

class LibraryPageViewModel:ViewModel() {

    private val serverRepo = ServerRepo()
    val library: MutableLiveData<List<MusicDTO>?> = MutableLiveData()
    private val downloadedCovers = mutableMapOf<String, Bitmap>()

    fun getLibraryFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            serverRepo.getLibrary() { result ->
                if (result.isNullOrEmpty()) {
                    return@getLibrary
                } else {
                    for (r in result){
                        if (downloadedCovers.containsKey(r.album[0].title)){
                            r.cover.postValue(downloadedCovers[r.album[0].title])
                        } else{
                            getCoverFromRepo(r.album[0].cover_path, r)
                        }
                    }
                    library.postValue(result)
                }
            }
        }
    }

    private fun getCoverFromRepo(path: String, music: MusicDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            serverRepo.getResource(path) { result ->
                val bitmap = BitmapFactory.decodeByteArray(result, 0, result!!.size)
                music.cover.postValue(bitmap)
                downloadedCovers[music.album[0].title] = bitmap
            }
        }
    }

//    fun getResource(id: Int): ByteArray? {
//        var response: ByteArray? = null
//        viewModelScope.launch {
//            serverRepo.getResource(id) { result ->
//                response = result
//            }
//        }
//        return response
//    }
}