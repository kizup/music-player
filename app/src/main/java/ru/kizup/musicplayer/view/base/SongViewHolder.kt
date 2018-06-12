package ru.kizup.musicplayer.view.base

import android.view.View
import android.widget.TextView
import ru.kizup.musicplayer.R
import ru.kizup.musicplayer.utils.ext.findView

/**
 * Created by: kizup on 10.06.18.
 * Skype: kizupx
 */
class SongViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val songTitleTextView: TextView = findView(R.id.tv_song_title)
    val songIndexTextView: TextView = findView(R.id.tv_song_index)
    val songDurationTextView: TextView = findView(R.id.tv_song_duration)

}