package ru.surok.clientserverappproject.views.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.surok.clientserverappproject.data.models.MusicSample
import ru.surok.clientserverappproject.data.repos.ServerRepo
import java.io.InputStream

class LibraryPageViewModel:ViewModel() {

    private val serverRepo = ServerRepo()
    var library: MutableLiveData<List<MusicSample>?> = MutableLiveData()

    fun getLibraryFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            serverRepo.getLibrary() { result ->
                val l = result?.result
                if (l.isNullOrEmpty()) {
                    return@getLibrary
                } else {
                    for (i in l) {
                        i.coverStream = getCover(i.fileId)
                    }
                    library.postValue(l)
                }
            }
        }
    }

    fun getCover(id: Int): ByteArray? {
        var response: ByteArray? = null
        viewModelScope.launch {
            serverRepo.getMusicCover(id) { result ->
                response = result
            }
        }
        return response
    }
}