package ru.kizup.musicplayer.model.pojo

import android.net.Uri

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
data class Song(val uri: Uri,
                val title: String?,
                val album: Album,
                val artist: Artist?,
                val duration: Int?,
                val displayName: String?,
                val size: Long?) {

    override fun toString(): String {
        return "Song(title=$title, album=$album, artist=$artist, duration=$duration, displayName=$displayName, size=$size)"
    }

}
