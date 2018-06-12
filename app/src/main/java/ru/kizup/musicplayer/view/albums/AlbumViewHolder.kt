package ru.kizup.musicplayer.view.albums

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import ru.kizup.musicplayer.R
import ru.kizup.musicplayer.utils.ext.findView
import ru.kizup.musicplayer.view.base.BaseViewHolder

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
class AlbumViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val cardView: CardView = itemView as CardView
    val artImageView: ImageView = findView(R.id.iv_album_art)
    val titleTextView: TextView = findView(R.id.tv_album_title)

}