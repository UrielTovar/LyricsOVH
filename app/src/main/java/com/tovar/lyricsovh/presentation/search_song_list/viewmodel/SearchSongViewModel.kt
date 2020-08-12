package com.tovar.lyricsovh.presentation.search_song_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tovar.lyricsovh.domain.search_song_list.SearchSongsInteractor
import com.tovar.lyricsovh.presentation.resource.Resource
import kotlinx.coroutines.Dispatchers

class SearchSongViewModel(
    private val searchSongsInteractor: SearchSongsInteractor
) : ViewModel() {

    fun getSongsBy(text: String) = liveData(Dispatchers.IO) {
        if (text != "") {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        searchSongsInteractor.getSongsByText(text = text)
                    )
                )
            }catch(exception: Exception) {
                emit(Resource.error(data = null,message = exception.message ?: "Error occurred"))
            }
        }
    }

}