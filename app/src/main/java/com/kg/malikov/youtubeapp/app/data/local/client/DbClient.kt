package com.kg.malikov.youtubeapp.app.data.local.client

import android.content.Context
import androidx.room.Room
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListDao
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListInfoDao
import com.kg.malikov.youtubeapp.app.data.local.db.Database


class DbClient {
    internal fun provideDb(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "db"
        )
            .build()
    }
    fun providePlayListsDao(database: Database): PlayListDao?=database.playListsDao()
    fun providePlayListInfoDao(database: Database): PlayListInfoDao?=database.playListInfoDao()

}
