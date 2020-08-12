package com.tovar.lyricsovh.presentation.search_song_list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tovar.lyricsovh.R
import com.tovar.lyricsovh.data.data_source.network.search_song_list.repository.GetSongsNetworkRepository
import com.tovar.lyricsovh.databinding.ActivitySearchSongBinding
import com.tovar.lyricsovh.domain.search_song_list.SearchSongsInteractor
import com.tovar.lyricsovh.presentation.resource.Status.*
import com.tovar.lyricsovh.presentation.search_song_list.adapter.SearchSongsAdapter
import com.tovar.lyricsovh.presentation.search_song_list.viewmodel.SearchSongViewModel
import com.tovar.lyricsovh.presentation.search_song_list.viewmodel.SearchSongViewModelFactory
import kotlinx.android.synthetic.main.activity_search_song.*

class SearchSongActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private lateinit var binding: ActivitySearchSongBinding
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search_song)
        binding.viewModel = ViewModelProvider(
            this,
            SearchSongViewModelFactory(
                SearchSongsInteractor(GetSongsNetworkRepository())
            )
        ).get(SearchSongViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_song_menu, menu)
        configureSearchIn(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun configureSearchIn(menu: Menu){
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = getString(R.string.search_input_hint)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        onInputTextChange(newText ?: "")
        return false
    }

    override fun onClose(): Boolean {
        binding.tvPlaceHolder.visibility = View.VISIBLE
        binding.rvSongsList.visibility = View.GONE
        return false
    }

    private fun onInputTextChange(text: String?) {
        binding.viewModel?.getSongsBy(text!!)?.observe(
            this,
            Observer {
                it?.let { resource ->
                    when(resource.status) {
                        SUCCESS -> {
                            binding.pbSearchSongs.visibility = View.GONE
                            binding.tvError.visibility = View.GONE
                            binding.tvPlaceHolder.visibility = View.GONE
                            resource.data?.data?.let { list ->
                                rvSongsList.apply {
                                    adapter =SearchSongsAdapter(list)
                                    layoutManager = LinearLayoutManager(this@SearchSongActivity)
                                    addItemDecoration(
                                        DividerItemDecoration(this@SearchSongActivity, DividerItemDecoration.VERTICAL)
                                    )
                                }
                                binding.rvSongsList.visibility = View.VISIBLE
                            }
                        }

                        ERROR -> {
                            binding.pbSearchSongs.visibility = View.GONE
                            binding.tvPlaceHolder.visibility = View.GONE
                            binding.tvError.visibility = View.VISIBLE
                            binding.tvError.text = resource.message
                        }

                        LOADING -> {
                            binding.pbSearchSongs.visibility = View.VISIBLE
                            binding.tvPlaceHolder.visibility = View.GONE
                            binding.tvError.visibility = View.GONE
                            binding.rvSongsList.visibility = View.GONE
                        }
                    }
                }
            }
        )
    }

    override fun onBackPressed() {
        if(searchView.isIconified)
            super.onBackPressed()
        else searchView.onActionViewCollapsed()
    }
}