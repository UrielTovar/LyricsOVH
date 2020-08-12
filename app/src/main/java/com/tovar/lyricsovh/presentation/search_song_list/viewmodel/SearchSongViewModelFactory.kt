package com.tovar.lyricsovh.presentation.search_song_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tovar.lyricsovh.domain.search_song_list.SearchSongsInteractor

class SearchSongViewModelFactory(
    private val searchSongsInteractor: SearchSongsInteractor
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SearchSongViewModel(
            searchSongsInteractor = searchSongsInteractor
        ) as T
    }
}