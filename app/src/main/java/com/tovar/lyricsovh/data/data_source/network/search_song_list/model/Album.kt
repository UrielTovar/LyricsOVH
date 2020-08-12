package com.tovar.lyricsovh.data.data_source.network.search_song_list.model

data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Int,
    val title: String,
    val trackList: String,
    val type: String
)