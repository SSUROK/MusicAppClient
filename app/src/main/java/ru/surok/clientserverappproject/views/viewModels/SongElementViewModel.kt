//package ru.surok.clientserverappproject.views.viewModels
//
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
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
//    var img: MutableLiveData<Bitmap> = MutableLiveData()
//
//    fun getCover(path: String) {
//        viewModelScope.launch {
//            serverRepo.getResource(path) { result ->
//                val drawable = BitmapFactory.decodeByteArray(result, 0, result!!.size)
//                img.postValue(drawable)
//            }
//        }
//    }
//}