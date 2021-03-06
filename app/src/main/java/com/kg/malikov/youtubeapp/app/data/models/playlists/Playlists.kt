package com.kg.malikov.youtubeapp.app.data.models.playlists

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlists(
@PrimaryKey(autoGenerate = true)
    var id: Int?,
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String = "",
    var items: MutableList<PlaylistItem>? = null,
)

data class PlaylistItem(
    var kind: String? = null,
    var etag: String? = null,
    var id: String? = null,
    var snippet: Snippet? = null,
    var contentDetails: ContentDetails? = null,
    var item: MutableList<Item>?,
    var nextPageToken: String? = null,
)

data class Snippet(
    var publishedAt: String? = null,
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null,
    var resourceIdId: ResourceId
)

data class Thumbnails(
    var medium: Medium? = null
)

data class Medium(
    var url: String? = null
)

data class ContentDetails(
    var itemCount: String? = null
)

data class Item(
    var kind: String?,
    var etag: String?,
    var id: String?,
    var snippet: Snippet?
)

data class ResourceId(
    var videoId: String?
)
