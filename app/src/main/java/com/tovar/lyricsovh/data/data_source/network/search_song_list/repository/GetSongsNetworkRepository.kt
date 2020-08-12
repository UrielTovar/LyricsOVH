package com.tovar.lyricsovh.data.data_source.network.search_song_list.repository

import com.tovar.lyricsovh.data.data_source.network.NetworkModule
import com.tovar.lyricsovh.data.data_source.network.search_song_list.model.GetSongsResponse
import com.tovar.lyricsovh.data.data_source.network.search_song_list.service.GetSongsService
import com.tovar.lyricsovh.data.data_source.utils.Configurations
import com.tovar.lyricsovh.domain.search_song_list.SearchSongsRepository

class GetSongsNetworkRepository : SearchSongsRepository {

    private var retrofitInstance =
        NetworkModule().provideRetrofit(baseURL = Configurations().getBaseUrl())

    override suspend fun getSongsByText(text: String): GetSongsResponse {
      return NetworkModule().provideApi(
            retrofit = retrofitInstance, service = GetSongsService::class.java
        ).getSuggestedSongsByText(text = text)
    }
}