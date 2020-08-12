package com.tovar.lyricsovh.data.data_source.network.song_lyric.repository

import com.tovar.lyricsovh.data.data_source.network.NetworkModule
import com.tovar.lyricsovh.data.data_source.network.song_lyric.model.GetSongLyricsResponse
import com.tovar.lyricsovh.data.data_source.network.song_lyric.service.GetSongLyricsService
import com.tovar.lyricsovh.data.data_source.utils.Configurations
import com.tovar.lyricsovh.domain.song_lyric.GetSongLyricsRepository

class GetSongLyricsNetworkRepository: GetSongLyricsRepository {

    private var retrofitInstance =
        NetworkModule().provideRetrofit(baseURL = Configurations().getBaseUrl())

    override suspend fun getLyricsBy(artist: String, song: String): GetSongLyricsResponse {
        return NetworkModule().provideApi(
            retrofit = retrofitInstance, service = GetSongLyricsService::class.java
        ).getLyricByArtistAndTitleSong(artist = artist, song = song)
    }

}