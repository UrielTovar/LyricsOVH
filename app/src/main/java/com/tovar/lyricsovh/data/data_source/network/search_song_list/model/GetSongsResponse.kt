package com.tovar.lyricsovh.data.data_source.network.search_song_list.model

data class GetSongsResponse(
    val data: List<Data>,
    val next: String,
    val total: Int
)