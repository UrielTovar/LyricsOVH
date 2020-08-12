package com.tovar.lyricsovh.data.data_source.network.search_song_list.service

import com.tovar.lyricsovh.data.data_source.network.search_song_list.model.GetSongsResponse
import com.tovar.lyricsovh.data.data_source.network.search_song_list.service.GetSongsService.URL.GET_SUGGEST_SONGS
import retrofit2.http.GET
import retrofit2.http.Path

interface GetSongsService {

    private object URL {
        const val GET_SUGGEST_SONGS: String = "/suggest/{search_term}"
    }

    @GET(value = GET_SUGGEST_SONGS)
    suspend fun getSuggestedSongsByText(
        @Path("search_term") text: String
    ): GetSongsResponse
}