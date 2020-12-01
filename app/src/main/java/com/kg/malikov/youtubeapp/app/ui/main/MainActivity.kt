package com.kg.malikov.youtubeapp.app.ui.main

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.base.BaseActivity
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import com.kg.malikov.youtubeapp.app.ui.info.InfoPlayListActivity
import com.kg.malikov.youtubeapp.app.ui.main.adapter.PlayListsAdapter
import com.kg.malikov.youtubeapp.app.utils.changeLanguage
import com.kg.malikov.youtubeapp.app.utils.loadLocale
import com.kg.malikov.youtubeapp.app.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity :
    BaseActivity<PlayListsViewModel>(R.layout.activity_main) {
    private lateinit var adapter: PlayListsAdapter
    private lateinit var toolbar: Toolbar
    override val viewModel by inject<PlayListsViewModel>()

    override fun setupLiveData() {
        Log.e("ololo", "setupLiveData: ")
        subscribePlayLists()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflatare = menuInflater
        inflatare.inflate(R.menu.change_language, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_change_btn->{
                changeLanguage()
            }

        }
        return true

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