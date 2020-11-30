package com.kg.malikov.youtubeapp.app.data.local.conv

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.Item
import java.lang.reflect.Type

object PlayListInfoConverters{
    private val gson = Gson()
    private val type: Type = object : TypeToken<MutableList<Item>>() {}.type

    @TypeConverter
    @JvmStatic
    fun daysOfWeekToString(playlistItems: MutableList<Item>?): String? =
        gson.toJson(playlistItems, type)

    @TypeConverter
    @JvmStatic
    fun stringToDaysOfWeek(playlistItems: String?): MutableList<Item>? =
        gson.fromJson(playlistItems, type)
}