//package ru.surok.clientserverappproject.views.viewModels
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//import ru.surok.clientserverappproject.data.repos.ServerRepo
//
//class SongElementViewModel:ViewModel() {
//
//    private val serverRepo = ServerRepo()
//    var img: LiveData<ByteArray>? = null
//
//    fun getCover(id: Int) {
//        viewModelScope.launch {
//            serverRepo.getMusicCover(id) { result ->
//                img = MutableLiveData(result)
//            }
//        }
//    }
//}