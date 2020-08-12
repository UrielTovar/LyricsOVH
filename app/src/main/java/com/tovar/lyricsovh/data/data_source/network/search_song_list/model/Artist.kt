package com.tovar.lyricsovh.data.data_source.network.search_song_list.model

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val trackList: String,
    val type: String
)