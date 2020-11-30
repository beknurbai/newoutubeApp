package com.kg.malikov.youtubeapp.app.data.local.conv

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import java.lang.reflect.Type

object  PlayListsConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<MutableList<PlaylistItem>>() {}.type

    @TypeConverter
    @JvmStatic
    fun daysOfWeekToString(playlistItems: MutableList<PlaylistItem>?): String? =
        gson.toJson(playlistItems, type)

    @TypeConverter
    @JvmStatic
    fun stringToDaysOfWeek(playlistItems: String?): MutableList<PlaylistItem>? =
        gson.fromJson(playlistItems, type)
}