package com.kg.malikov.youtubeapp.app.base


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.kg.malikov.youtubeapp.app.R
import com.kg.malikov.youtubeapp.app.utils.loadLocale

abstract class BaseActivity<out ViewModel : BaseViewModel>(
    private var layot: Int
) : AppCompatActivity() {
   abstract  val viewModel:ViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layot)
        setupViews()
        setupLiveData()
        setupFetchRequests()
    }

    override fun onResume() {
        loadLocale(this)
        super.onResume()

    }

    abstract fun setupLiveData()
    abstract fun setupViews()
    abstract fun setupFetchRequests()

    private fun showError() {
        viewModel.errorMessage.observeForever {
        }

    }
}