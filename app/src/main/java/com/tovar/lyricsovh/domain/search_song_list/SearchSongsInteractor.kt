package com.tovar.lyricsovh.domain.search_song_list

import com.tovar.lyricsovh.data.data_source.network.search_song_list.model.GetSongsResponse

class SearchSongsInteractor(private var searchSongsRepository: SearchSongsRepository) {

    suspend fun getSongsByText(text: String): GetSongsResponse {
        return  searchSongsRepository.getSongsByText(text = text)
    }

}