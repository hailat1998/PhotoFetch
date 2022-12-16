package com.hd1998.photofetch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hd1998.photofetch.api.PhotoItem
import com.hd1998.photofetch.api.Photos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.copy

class PhotoViewModel : ViewModel() {
   val photoRepo : PhotoRepo = PhotoRepo()
    var _uistate : MutableStateFlow<PhotoUIState> = MutableStateFlow(PhotoUIState())
    val uiState : StateFlow<PhotoUIState>
    get() =_uistate .asStateFlow()
    init {
        viewModelScope.launch {
          val item = photoRepo.photoFetch().photoItems
            _uistate.update { oldState
            -> oldState.copy(photoItems = item)
            }
        }
        }
    }

data class PhotoUIState(val photoItems: List<PhotoItem> = listOf())