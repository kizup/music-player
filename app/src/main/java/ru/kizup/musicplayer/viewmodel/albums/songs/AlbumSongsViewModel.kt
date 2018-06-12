package ru.kizup.musicplayer.viewmodel.albums.songs

import android.arch.lifecycle.ViewModel
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.model.pojo.Song
import ru.kizup.musicplayer.model.source.ISongsDataSource

/**
 * Created by: kizup on 10.06.18.
 * Skype: kizupx
 */
class AlbumSongsViewModel(private val dataSource: ISongsDataSource) : ViewModel() {

    lateinit var album: Album

    fun getAlbumSongs(): List<Song> = dataSource.loadAlbumSongs(album)

}