package com.kg.malikov.youtubeapp.app.ui.info

import androidx.lifecycle.MutableLiveData
import com.kg.malikov.youtubeapp.app.base.BaseViewModel
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.Item
import com.kg.malikov.youtubeapp.app.data.models.playlistinfo.PlaylistInfo
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import com.kg.malikov.youtubeapp.app.data.network.Status
import com.kg.malikov.youtubeapp.app.repository.YoutubeRepository

class InfoPlaylistViewModel(var repository: YoutubeRepository) : BaseViewModel(){
    var playlist: PlaylistItem? = null


    var     detailPlaylists = MutableLiveData<PlaylistInfo>()
    var detail: MutableList<Item>? = mutableListOf()
    var playlistId: String? = null
    fun fetchPlaylistVideo(playlistId: String?, nextPageToken: String? = null) {
        this.playlistId = playlistId
        repository.fetchInfoPlaylists(playlistId, nextPageToken).observeForever {
            when (it.status) {
                Status.SUCCESS -> detailPlaylists.value=it.data!!
                Status.ERROR -> errorMessage.value = it.message.toString()
            }
        }
    }
    fun fetchPlaylistVideoWithoutNetwork(playlistId: String?, nextPageToken: String? = null) {
        this.playlistId = playlistId
        repository.fetchInfoPlaylistDb(playlistId, nextPageToken).observeForever {
            when (it.status) {
                Status.SUCCESS -> detailPlaylists.value= it.data
                Status.ERROR -> errorMessage.value = it.message.toString()
            }
        }
    }




   /* private fun getAllVideo(data: Item?) {
        data
        .let {
                detail?.
                addAll(it)
        }
        if (!data?.nextPageToken.isNullOrEmpty()) fetchPlaylistVideo(playlistId, data?.nextPageToken)
        else detailPlaylists.value = detail
    }*/
}