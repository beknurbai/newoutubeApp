package com.kg.malikov.youtubeapp.app.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

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

    abstract fun setupLiveData()
    abstract fun setupViews()
    abstract fun setupFetchRequests()

    private fun showError() {
        viewModel.errorMessage.observeForever {
        }
    }
}