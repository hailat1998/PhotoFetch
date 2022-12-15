package com.hd1998.photofetch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hd1998.photofetch.api.PhotoItem
import com.hd1998.photofetch.api.Photos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
   val photoRepo : PhotoRepo = PhotoRepo()
    var _uistate : MutableStateFlow<List<PhotoItem>> = MutableStateFlow(emptyList())
    val uiState : StateFlow<List<PhotoItem>> = _uistate .asStateFlow()
    init {
        viewModelScope.launch {
          val item = photoRepo.photoFetch().photoItems
            _uistate.value = item
        }
    }
}
data class PhotoUIState(val photoItem: PhotoItem)