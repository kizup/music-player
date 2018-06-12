package ru.kizup.musicplayer.utils

import ru.kizup.musicplayer.model.pojo.Album

/**
 * Created by: kizup on 10.06.18.
 * Skype: kizupx
 */

interface OnAlbumClickListener {
    fun onAlbumClick(album: Album)
}

interface OnItemClickListener<Item: Any> {
    fun onItemClick(item: Item): Unit
}