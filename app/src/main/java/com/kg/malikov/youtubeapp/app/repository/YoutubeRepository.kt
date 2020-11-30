package com.kg.malikov.youtubeapp.app.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListDao
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListInfoDao
import com.kg.malikov.youtubeapp.app.data.network.Resource
import com.kg.malikov.youtubeapp.app.data.network.YoutubeApi
import com.kg.malikov.youtubeapp.app.utils.CHANNEL_ID
import com.kg.malikov.youtubeapp.app.utils.KEY
import com.kg.malikov.youtubeapp.app.utils.MAX_RESULT
import com.kg.malikov.youtubeapp.app.utils.PART
import kotlinx.coroutines.Dispatchers

open class BaseRepository {

    suspend fun <T> baseRequest(dto: T) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dto))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun <T> baseRequestWithDB(dto: T, fetchData: T) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.fetchFromDB(fetchData))
        try {
            emit(Resource.success(data = dto))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}


class YoutubeRepository(
    private var api: YoutubeApi,
    private var dao: PlayListDao,
    private var daoInfo: PlayListInfoDao
) : BaseRepository() {
    fun fetchPlaylists(pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        //emit(Resource.fetchFromDB(dao.getPlaylist()))
        try {
            val request = api.fetchPlayLists(PART, KEY, CHANNEL_ID, MAX_RESULT, pageToken)
            dao.insert(request)
            emit(Resource.success(data = request))
            Log.d("ololo", "olol: "+dao.getPlaylistByNextPageToken(pageToken) )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun fetchPlaylistsDb(pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.fetchFromDB(dao.getPlaylist()))
        try {
            val request = api.fetchPlayLists(PART, KEY, CHANNEL_ID, MAX_RESULT, pageToken)
            emit(Resource.success(data = request))
            Log.d("ololo", "olol: "+dao.getPlaylistByNextPageToken(pageToken) )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }




    fun fetchInfoPlaylists(playlistId: String?, pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result= api.fetchPlaylistById(PART, KEY, playlistId, pageToken)
            result.let {
                result.playlistApiId=result.items[0].snippet.playlistId
                daoInfo.insertDetailsPlaylist(result)
            }
            emit(Resource.success(data =result ))
            Log.d("ololod", "fetchInfoPlaylists: "+daoInfo.getDetailsPlaylistById(playlistId) )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
    fun fetchInfoPlaylistDb(playlistId: String?, pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.fetchFromDB(daoInfo.getDetailsPlaylistById(playlistId)))
        try {
            val result= api.fetchPlaylistById(PART, KEY, playlistId, pageToken)
            emit(Resource.success(data = result ))
            Log.d("ololos", "fetchInfoPlaylists: "+daoInfo.getDetailsPlaylistById(playlistId) )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

}