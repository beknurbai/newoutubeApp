package com.kg.malikov.youtubeapp.app.ui.info.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.Item
import com.kg.malikov.youtubeapp.app.interfaces.ItemClick
import com.kg.malikov.youtubeapp.app.utils.gone
import com.kg.malikov.youtubeapp.app.utils.loadImage
import kotlinx.android.synthetic.main.item_playlist.view.*

class InfoPlaylistAdapter(
    var data: MutableList<Item>
) : RecyclerView.Adapter<InfoPlaylistAdapter.InfoPlayListHolder>() {
    private var listener: ItemClick? = null
    fun setListener(listener: ItemClick) {
        this.listener = listener
    }
    fun addItems(items: MutableList<Item>) {
        data = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoPlayListHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_playlist, parent, false)
        return InfoPlayListHolder(view)

    }

    override fun onBindViewHolder(holder: InfoPlayListHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class InfoPlayListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.item_playlist_text_view_hint.gone()
            itemView.item_playlist_text_view_title_playList.text = item.snippet.title
            itemView.item_playlist_text_view_amount_video.text = item.snippet.description
            itemView.item_playlist_image_playlist.loadImage(item.snippet.thumbnails.medium?.url!!)
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition)
            }

        }


    }
}