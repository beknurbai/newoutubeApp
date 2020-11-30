package com.kg.malikov.youtubeapp.app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel(){
    var errorMessage = MutableLiveData<String>()
}