package com.kg.malikov.youtubeapp.app.ui.main

import androidx.lifecycle.MutableLiveData
import com.kg.malikov.youtubeapp.app.base.BaseViewModel
import com.kg.malikov.youtubeapp.app.data.local.dao.PlayListDao
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import com.kg.malikov.youtubeapp.app.data.network.Status
import com.kg.malikov.youtubeapp.app.repository.YoutubeRepository

class PlayListsViewModel(var repository: YoutubeRepository) : BaseViewModel(){
    var playlists = MutableLiveData<MutableList<PlaylistItem>>()

    init {
        fetchPlaylists(null)
        fetchPlaylistWithoutNetwork()
    }
    private fun fetchPlaylists(pageToken:String?) {
        repository.fetchPlaylists(pageToken).observeForever {
            when (it.status) {
                Status.SUCCESS -> playlists.postValue(it.data?.items)
                Status.ERROR -> errorMessage.postValue(it.message.toString())
            }
        }
    }
    private fun fetchPlaylistWithoutNetwork() {
        repository.fetchPlaylistsDb(null).observeForever{
            when (it.status) {
                Status.SUCCESS -> playlists.postValue(it.data?.items)
                Status.ERROR -> errorMessage.postValue(it.message.toString())
            }
        }
    }
}