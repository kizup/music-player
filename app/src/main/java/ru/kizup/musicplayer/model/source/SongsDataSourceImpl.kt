package ru.kizup.musicplayer.model.source

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.model.pojo.Artist
import ru.kizup.musicplayer.model.pojo.Song

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
class SongsDataSourceImpl(context: Context) : ISongsDataSource {

    private val contentResolver = context.contentResolver

    override fun loadAllSongs(): List<Song> = listOf()

    override fun loadAllAlbums(): List<Album> {
        val cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "${MediaStore.Audio.Media.IS_MUSIC} = ?", arrayOf("1"), null)
        val albums = mutableListOf<Album>()
        val albumsMap = hashMapOf<String, Album>()
        if (cursor != null && cursor.moveToFirst()) {
            val albumTitleIndex = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM)
            val albumIdIndex = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID)
            val albumKeyIndex = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_KEY)
            do {
                val title = cursor.getString(albumTitleIndex)
                val id = cursor.getInt(albumIdIndex)
                val key = cursor.getString(albumKeyIndex)
                val artUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), id.toLong())
                val album = Album(title, id, key, artUri = artUri)
                albumsMap[key] = album
            } while (cursor.moveToNext())
            cursor.close()
        }
        albums.addAll(albumsMap.values)
        return albums
    }

    override fun loadAllArtists(): List<Artist> = listOf()

    override fun loadAlbumSongs(album: Album): List<Song> {
        val cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
                "${MediaStore.Audio.Media.ALBUM_ID} = ? AND ${MediaStore.Audio.Media.IS_MUSIC} = ?",
                arrayOf(album.id.toString(), "1"),
                null)
        val songs = mutableListOf<Song>()

        if (cursor != null && cursor.moveToFirst()) {
            val title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val displayName = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
            val size = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)
            val path = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
            do {
                val artist = Artist(cursor.getString(artistColumn))
                songs.add(Song(Uri.parse(cursor.getString(path)),
                        cursor.getString(title),
                        album,
                        artist,
                        cursor.getInt(duration),
                        cursor.getString(displayName),
                        cursor.getLong(size)))
            } while (cursor.moveToNext())
            cursor.close()
        }
        return songs
    }

    override fun loadArtistSongs(artist: Artist): List<Song> = listOf()
}