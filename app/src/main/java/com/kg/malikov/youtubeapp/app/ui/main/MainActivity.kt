package com.kg.malikov.youtubeapp.app.ui.main

import android.util.Log
import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.base.BaseActivity
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import com.kg.malikov.youtubeapp.app.ui.info.InfoPlayListActivity
import com.kg.malikov.youtubeapp.app.ui.main.adapter.PlayListsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity :
    BaseActivity<PlayListsViewModel>(R.layout.activity_main) {
    private lateinit var adapter: PlayListsAdapter
    override val viewModel by inject<PlayListsViewModel> ()

     override fun setupLiveData() {
         Log.e("ololo", "setupLiveData: ", )
        subscribePlayLists()
    }

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        adapter = PlayListsAdapter(this::onItemClick)
        playListsFragment_recyclerview.adapter = adapter
    }

    override fun setupFetchRequests() {
    }

    private fun subscribePlayLists() {

        Log.e("ololo", "subscribePlayLists: observe")
        viewModel.playlists.observeForever {
            Log.e("ololo", "subscribePlayLists: observe")
            adapter.addItems(it)
            Log.e("ololo", "subscribePlayLists: "+adapter.data)
        }
    }

    private fun onItemClick(item: PlaylistItem) {
        InfoPlayListActivity.instanceActivity(this, item)
    }

}