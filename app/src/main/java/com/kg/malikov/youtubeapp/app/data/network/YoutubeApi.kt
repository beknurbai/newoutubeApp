package com.kg.malikov.youtubeapp.app.data.network

import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.PlaylistInfo
import com.kg.malikov.youtubeapp.app.data.models.playlists.Playlists
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("youtube/v3/playlists")
    suspend fun fetchPlayLists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String,
        @Query("maxResult") maxResult: String,
        @Query("pageToken") pageToken: String?
    ): Playlists

    @GET("youtube/v3/playlistItems")
    suspend fun fetchPlaylistById(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("playlistId") playlistId: String?,
        @Query("pageToken") pageToken: String?,
        ): PlaylistInfo


}