package com.hd1998.photofetch

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hd1998.photofetch.api.PhotoItem
import com.hd1998.photofetch.api.Photos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.copy

@SuppressLint("SuspiciousIndentation")
class PhotoViewModel : ViewModel() {
   val photoRepo : PhotoRepo = PhotoRepo()
    var _uistate : MutableStateFlow<PhotoUIState> = MutableStateFlow(PhotoUIState())
    val uiState : StateFlow<PhotoUIState>
    get() =_uistate .asStateFlow()
    init {
        try{
        viewModelScope.launch {
          val item = fetchPhoto("")
            _uistate.update { oldState -> oldState.copy(photoItems = item)
            }
        }
        }
    catch(e : java.lang.Exception){


    }}
   suspend fun fetchPhoto(query : String = "") : List<PhotoItem> {

          if(!query.isEmpty()){
        return photoRepo.photoSearch()   }

       return photoRepo.photoFetch()
   }
    }

data class PhotoUIState(val photoItems: List<PhotoItem> = listOf())