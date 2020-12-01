package com.kg.malikov.youtubeapp.app.ui.detail_video

import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.base.BaseActivity
import com.kg.malikov.youtubeapp.app.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail_video.*
import org.koin.android.ext.android.inject

class DetailVideoActivity : BaseActivity<DetailVideoViewModel>(R.layout.activity_detail_video) {
    override val viewModel by inject<DetailVideoViewModel>()
    override fun setupLiveData() {

    }

    override fun setupViews() {
        bindView()
    }

    private fun bindView() {
        val title = intent.getSerializableExtra("title")
        val desc = intent.getSerializableExtra("desc")
        val url = intent.getSerializableExtra("image")
        text_view_title.text = title.toString()
        text_view_description.text = desc.toString()
        imageView_video.loadImage(url = url.toString())


    }

    override fun setupFetchRequests() {

    }

}
