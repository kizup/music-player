package ru.kizup.musicplayer

import android.app.Application
import android.content.Intent
import com.squareup.picasso.Picasso
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import ru.kizup.musicplayer.model.source.ISongsDataSource
import ru.kizup.musicplayer.model.source.SongsDataSourceImpl
import ru.kizup.musicplayer.view.albums.AlbumsAdapter
import ru.kizup.musicplayer.view.base.SongsAdapter
import ru.kizup.musicplayer.viewmodel.albums.AlbumsViewModel
import ru.kizup.musicplayer.viewmodel.albums.songs.AlbumSongsViewModel

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
class MusicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, arrayListOf(modules))
    }

    private val modules: Module = {
        org.koin.dsl.module.applicationContext {
            bean { SongsDataSourceImpl(androidApplication()) as ISongsDataSource }
            bean {
                val picassoBuilder = Picasso.Builder(androidApplication())
                picassoBuilder.loggingEnabled(true)
                val picasso = picassoBuilder.build()
                Picasso.setSingletonInstance(picasso)
                return@bean picasso
            }
            factory { AlbumsAdapter(get()) }
            factory { SongsAdapter() }
            viewModel { AlbumsViewModel(get()) }
            viewModel { AlbumSongsViewModel(get()) }
        }.invoke()
    }

}