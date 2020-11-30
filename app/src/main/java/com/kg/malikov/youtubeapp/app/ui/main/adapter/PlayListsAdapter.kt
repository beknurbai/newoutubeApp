package com.kg.malikov.youtubeapp.app.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import com.kg.malikov.youtubeapp.app.utils.loadImage
import com.kg.malikov.youtubeapp.app.interfaces.ItemClick
import kotlinx.android.synthetic.main.item_playlist.view.*

class PlayListsAdapter(
    private var onItemClick: (PlaylistItem) -> Unit
) : RecyclerView.Adapter<PlayListsAdapter.PlayListsViewHolder>() {
    private var listener: ItemClick? = null
    var data = mutableListOf<PlaylistItem>()
    fun addItems(items: MutableList<PlaylistItem>) {
        data = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlayListsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayListsViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onItemClick(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlayListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(playlists: PlaylistItem) {
            itemView.item_playlist_text_view_title_playList.text = playlists.snippet?.title
            itemView.item_playlist_text_view_amount_video.text = playlists.contentDetails?.itemCount
            playlists.snippet?.thumbnails?.medium?.url?.let {
                itemView.item_playlist_image_playlist.loadImage(it)
            }
        }

    }

}