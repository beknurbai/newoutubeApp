package com.kg.malikov.youtubeapp.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.Item
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.PlaylistInfo
import com.kg.malikov.youtubeapp.app.data.models.playlists.Playlists

@Dao
interface PlayListInfoDao {


    @Query("SELECT * FROM playlistinfo")
    suspend fun getAllDetailsPlaylist(): MutableList<PlaylistInfo>?

    @Query("SELECT * FROM playlistinfo WHERE playlistApiId = :id")
    suspend fun getDetailsPlaylistById(id: String?): PlaylistInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailsPlaylist(playlists: PlaylistInfo)

}