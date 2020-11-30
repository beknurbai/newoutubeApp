package com.kg.malikov.youtubeapp.app.di

import com.kg.malikov.youtubeapp.app.data.local.client.DbClient
import com.kg.malikov.youtubeapp.app.data.network.RetrofitClient
import com.kg.malikov.youtubeapp.app.repository.YoutubeRepository
import com.kg.malikov.youtubeapp.app.ui.info.InfoPlaylistViewModel
import com.kg.malikov.youtubeapp.app.ui.main.PlayListsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*var repositoryModule = module {
    factory { }
}*/
var networkModule = module {
    single { RetrofitClient() }
    single { RetrofitClient().instanceRetrofit() }
}
var viewModelModule= module {
    viewModel { PlayListsViewModel(get ()) }
    viewModel { InfoPlaylistViewModel(get()) }
}
var repositoryModule= module {
factory { YoutubeRepository(get(),get(),get()) }
}

var dbModule=module{
factory { DbClient().provideDb(androidContext()) }
factory { DbClient().providePlayListInfoDao(get()) }
factory { DbClient().providePlayListsDao(get()) }
}