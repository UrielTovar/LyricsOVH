package com.tovar.lyricsovh.presentation.song_lyric.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tovar.lyricsovh.domain.song_lyric.GetSongLyricsInteractor
import com.tovar.lyricsovh.presentation.resource.Resource
import kotlinx.coroutines.Dispatchers

class SongLyricViewModel(
    private val getSongLyricsInteractor: GetSongLyricsInteractor
) : ViewModel() {

    fun getLyricsBy(artist: String, song: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    getSongLyricsInteractor.getLyricsBy(
                        artist = artist,
                        song = song
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occurred"))
        }
    }

}