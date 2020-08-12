package com.tovar.lyricsovh.presentation.song_lyric

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tovar.lyricsovh.R
import com.tovar.lyricsovh.data.data_source.network.song_lyric.repository.GetSongLyricsNetworkRepository
import com.tovar.lyricsovh.databinding.ActivitySongLyricBinding
import com.tovar.lyricsovh.domain.song_lyric.GetSongLyricsInteractor
import com.tovar.lyricsovh.presentation.resource.Status.*
import com.tovar.lyricsovh.presentation.song_lyric.viewmodel.SongLyricViewModel
import com.tovar.lyricsovh.presentation.song_lyric.viewmodel.SongLyricViewModelFactory

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_song_lyric)
        binding.viewModel = ViewModelProvider(
            this,
            SongLyricViewModelFactory(
                GetSongLyricsInteractor(GetSongLyricsNetworkRepository())
            )
        ).get(SongLyricViewModel::class.java)

        artist = intent.getStringExtra(ARTIST).toString()
        song = intent.getStringExtra(SONG).toString()

        binding.viewModel?.getLyricsBy(artist = artist, song = song)?.observe(
            this,
            Observer {
                it.let { resource ->
                    when (resource.status) {
                        SUCCESS -> {
                            binding.pbLyricsSong.visibility = View.GONE
                            binding.tvLyricsError.visibility = View.GONE
                            resource.data?.let {
                                binding.tvSongDetail.apply {
                                    text = getString(R.string.artist_and_song_title, artist, song)
                                    visibility = View.VISIBLE
                                }
                                binding.svLyricsContainer.visibility = View.VISIBLE
                                binding.tvSongLyrics.apply {
                                    text = resource.data.lyrics
                                }
                            }
                        }

                        ERROR -> {
                            binding.pbLyricsSong.visibility = View.GONE
                            binding.tvLyricsError.visibility = View.VISIBLE
                            binding.tvLyricsError.text = resource.message
                        }

                        LOADING -> {
                            binding.pbLyricsSong.visibility = View.VISIBLE
                            binding.tvLyricsError.visibility = View.GONE
                            binding.svLyricsContainer.visibility = View.GONE
                            binding.tvSongDetail.visibility = View.GONE

                        }
                    }
                }
            }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}