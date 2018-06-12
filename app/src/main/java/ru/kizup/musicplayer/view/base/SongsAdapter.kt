package ru.kizup.musicplayer.view.base

import android.view.ViewGroup
import ru.kizup.musicplayer.R
import ru.kizup.musicplayer.model.pojo.Song
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by: kizup on 10.06.18.
 * Skype: kizupx
 */
class SongsAdapter : BaseAdapter<Song, SongViewHolder>() {

    private val durationFormat: DateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SongViewHolder {
        val holder = SongViewHolder(inflate(parent!!, R.layout.item_album_song))
        holder.itemView.setOnClickListener { listener?.onItemClick(items[holder.adapterPosition]) }
        return holder
    }

    override fun onBindViewHolder(holder: SongViewHolder?, position: Int) {
        val song = items[position]
        holder!!.songTitleTextView.text = song.title
        holder.songDurationTextView.text = durationFormat.format(Date(song.duration!!.toLong()))
        holder.songIndexTextView.text = position.plus(1).toString()
    }
}