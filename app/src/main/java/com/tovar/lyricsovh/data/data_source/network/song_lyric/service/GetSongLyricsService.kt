package com.tovar.lyricsovh.data.data_source.network.song_lyric.service

import com.tovar.lyricsovh.data.data_source.network.song_lyric.model.GetSongLyricsResponse
import com.tovar.lyricsovh.data.data_source.network.song_lyric.service.GetSongLyricsService.URL.GET_SONG_LYRICS
import retrofit2.http.GET
import retrofit2.http.Path

interface GetSongLyricsService {
    private object URL {
        const val GET_SONG_LYRICS: String = "/v1/{artist}/{song}"
    }

    @GET(GET_SONG_LYRICS)
    suspend fun getLyricByArtistAndTitleSong(
        @Path("artist") artist: String,
        @Path("song") song: String
    ) : GetSongLyricsResponse
}