package com.tovar.lyricsovh.presentation.search_song_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tovar.lyricsovh.R
import com.tovar.lyricsovh.data.data_source.network.search_song_list.model.Data

class SearchSongsAdapter(
    private val list: List<Data>
): RecyclerView.Adapter<SearchSongsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.song_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = list[position]
        holder.run {
            val context = itemView.context
            Glide.with(context).load(song.album.cover).into(ivAlbumCover)
            tvSongInfo.text =
                context.getString(R.string.artist_and_song_title, song.artist.name, song.title)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivAlbumCover: ImageView = itemView.findViewById(R.id.ivAlbumCover)
        val tvSongInfo: TextView = itemView.findViewById(R.id.tvSongInfo)
    }
}