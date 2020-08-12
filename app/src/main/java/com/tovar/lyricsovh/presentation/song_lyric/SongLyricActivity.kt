package com.tovar.lyricsovh.presentation.song_lyric

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tovar.lyricsovh.R
import com.tovar.lyricsovh.databinding.ActivitySongLyricBinding
import com.tovar.lyricsovh.presentation.song_lyric.viewmodel.SongLyricViewModel

class SongLyricActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongLyricBinding
    private lateinit var artist: String
    private lateinit var song: String

    companion object {

        private const val ARTIST: String = "ARTIST"
        private const val SONG: String = "SONG"

        fun launch(context: Context, artist: String, song: String) {
            context.startActivity(Intent(context, SongLyricActivity::class.java).apply {
                putExtra(ARTIST, artist)
                putExtra(SONG, song)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_song_lyric)
        binding.viewModel = ViewModelProvider(
            this
        ).get(SongLyricViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        artist = intent.getStringExtra(ARTIST).toString()
        song = intent.getStringExtra(SONG).toString()
        binding.tvSongDetail.apply {
            text = getString(R.string.artist_and_song_title, artist, song)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}