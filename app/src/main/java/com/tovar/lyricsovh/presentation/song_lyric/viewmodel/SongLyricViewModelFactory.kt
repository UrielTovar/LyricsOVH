package com.tovar.lyricsovh.presentation.song_lyric.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tovar.lyricsovh.domain.song_lyric.GetSongLyricsInteractor

class SongLyricViewModelFactory(
    private val getSongLyricsInteractor: GetSongLyricsInteractor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SongLyricViewModel(
            getSongLyricsInteractor = getSongLyricsInteractor
        ) as T
    }
}