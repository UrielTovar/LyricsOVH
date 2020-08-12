package com.tovar.lyricsovh.data.data_source.utils

class Configurations {

    private val currentConfiguration = "dev"
    private val configuration = mapOf(
        "dev" to Configuration(
            baseUrl = "https://api.lyrics.ovh"
        ),
        "qa" to Configuration(
            baseUrl = ""
        ),
        "release" to Configuration(
            baseUrl = ""
        )
    )

    private val baseUrl: String

    init {
        val selectedConf = configuration[currentConfiguration]
        baseUrl = selectedConf!!.baseUrl
    }

    fun getBaseUrl():String = baseUrl
}

private data class Configuration(
    val baseUrl: String = ""
)