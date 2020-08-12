package com.tovar.lyricsovh.domain.song_lyric

import com.tovar.lyricsovh.data.data_source.network.song_lyric.model.GetSongLyricsResponse

class GetSongLyricsInteractor(private var getSongLyricsRepository: GetSongLyricsRepository) {

    suspend fun getLyricsBy(artist: String, song: String): GetSongLyricsResponse {
        return getSongLyricsRepository.getLyricsBy(artist = artist, song = song)
    }

}