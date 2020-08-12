package com.tovar.lyricsovh.domain.search_song_list

import com.tovar.lyricsovh.data.data_source.network.search_song_list.model.GetSongsResponse

interface SearchSongsRepository {

    suspend fun getSongsByText(text: String): GetSongsResponse

}