package com.kg.malikov.youtubeapp.app.ui.info

import android.app.Activity
import android.content.Intent
import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.base.BaseActivity
import com.kg.malikov.youtubeapp.app.data.models.playlists.PlaylistItem
import com.kg.malikov.youtubeapp.app.interfaces.ItemClick
import com.kg.malikov.youtubeapp.app.ui.detail_video.DetailVideoActivity
import com.kg.malikov.youtubeapp.app.ui.info.adapter.InfoPlaylistAdapter
import kotlinx.android.synthetic.main.content_scrolling.*
import org.koin.android.ext.android.inject

class InfoPlayListActivity : BaseActivity<InfoPlaylistViewModel>(
    R.layout.activity_info_play_list
), ItemClick {
    override val viewModel by inject<InfoPlaylistViewModel>()
    private val adapter = InfoPlaylistAdapter(mutableListOf())



    override fun onClick(pos: Int) {
        val intent = Intent(this, DetailVideoActivity::class.java)
        intent.putExtra("title", adapter.data[pos].snippet.title)
        intent.putExtra("desc", adapter.data[pos].snippet.description)
        intent.putExtra("image", adapter.data[pos].snippet.thumbnails.medium?.url)
        startActivity(intent)
    }

    override fun setupLiveData() {
        setUpObserve()
    }


    override fun setupViews() {
        setUpRecycler()
    }

    override fun setupFetchRequests() {
        setUpData()
    }

    private fun setUpData() {
        viewModel.detailPlaylists.observeForever {
            adapter.addItems(it.items)
        }

    }

    private fun setUpObserve() {
        viewModel.fetchPlaylistVideo(playlist?.id, playlist?.nextPageToken)
        viewModel.fetchPlaylistVideoWithoutNetwork(playlist?.id, playlist?.nextPageToken)
    }

    private fun setUpRecycler() {
        adapter.setListener(this)
        playList_info_rec_view.adapter = adapter


    }

    companion object {
        var playlist: PlaylistItem? = null
        fun instanceActivity(activity: Activity?, playlist: PlaylistItem) {
            val intent = Intent(activity, InfoPlayListActivity::class.java)
            this.playlist = playlist
            activity?.startActivity(intent)
        }
    }
}