package com.kg.malikov.youtubeapp.app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kg.malikov.youtubeapp.app.data.local.conv.PlayListInfoConverters
import com.kg.malikov.youtubeapp.app.data.local.conv.PlayListsConverter
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListDao
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListInfoDao
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.PlaylistInfo
import com.kg.malikov.youtubeapp.app.data.models.playlists.Playlists

@Database(entities = [Playlists::class,PlaylistInfo::class],version = 2)
@TypeConverters(PlayListsConverter::class, PlayListInfoConverters::class)
abstract class Database(): RoomDatabase() {
    abstract fun playListsDao(): PlayListDao?
    abstract fun playListInfoDao(): PlayListInfoDao?

}