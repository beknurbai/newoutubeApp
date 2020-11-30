package com.kg.malikov.youtubeapp.app.data.models.playlistinfo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kg.malikov.youtubeapp.app.data.models.playlists.Thumbnails
@Entity
data class PlaylistInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var playlistApiId: String? = null,
    var items: MutableList<Item>,
    var nextPageToken: String = "",


)

data class Item(
    var snippet: Snippet
)

data class Snippet(
    var title: String,
    var description: String,
    var publishedAt: String,
    var playlistId: String? = null,
    var thumbnails: Thumbnails
)