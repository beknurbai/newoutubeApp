package com.kg.malikov.youtubeapp.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kg.malikov.youtubeapp.app.data.models.playlists.Playlists



@Dao
interface PlayListDao {


    @Query("SELECT * FROM playlists WHERE nextPageToken = :pageToken")
    suspend fun getPlaylistByNextPageToken(pageToken: String?): Playlists?

    @Insert
    fun insert(playlists: Playlists)
    @Query("SELECT * FROM playlists")
    suspend fun getPlaylist(): Playlists
}