package ru.kizup.musicplayer.viewmodel.albums

import android.arch.lifecycle.ViewModel
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.model.source.ISongsDataSource

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
class AlbumsViewModel(private val dataSource: ISongsDataSource) : ViewModel() {

    fun getAlbums(): List<Album> = dataSource.loadAllAlbums()

}