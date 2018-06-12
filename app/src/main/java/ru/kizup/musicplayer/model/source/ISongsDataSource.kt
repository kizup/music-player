package ru.kizup.musicplayer.model.source

import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.model.pojo.Artist
import ru.kizup.musicplayer.model.pojo.Song

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
interface ISongsDataSource {

    fun loadAllSongs(): List<Song>

    fun loadAllAlbums(): List<Album>

    fun loadAllArtists(): List<Artist>

    fun loadAlbumSongs(album: Album): List<Song>

    fun loadArtistSongs(artist: Artist): List<Song>

}