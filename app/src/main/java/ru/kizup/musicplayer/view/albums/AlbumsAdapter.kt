package ru.kizup.musicplayer.view.albums

import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.kizup.musicplayer.R
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.utils.getBlackWhiteColor
import ru.kizup.musicplayer.view.base.BaseAdapter
import java.lang.Exception

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
class AlbumsAdapter(private val picasso: Picasso) : BaseAdapter<Album, AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlbumViewHolder {
        val holder = AlbumViewHolder(inflate(parent!!, R.layout.item_album))
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener!!.onItemClick(items[holder.adapterPosition])
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: AlbumViewHolder?, position: Int) {
        val album = items[position]

        if (album.artUri != null) {
            picasso.load(album.artUri)
                    .placeholder(R.drawable.ic_library_music)
                    .into(holder!!.artImageView, object : Callback {
                        override fun onError(e: Exception?) {}

                        override fun onSuccess() {
                            val bd = holder.artImageView.drawable as BitmapDrawable
                            Palette.Builder(bd.bitmap)
                                    .generate {
                                        val swatch = it.vibrantSwatch
                                        if (swatch != null) {
                                            val rgb = swatch.rgb
                                            holder.cardView.setCardBackgroundColor(rgb)
                                            holder.titleTextView.setTextColor(getBlackWhiteColor(rgb))
                                        } else {
                                            val mutedSwatch = it.mutedSwatch
                                            if (mutedSwatch != null) {
                                                val rgb = mutedSwatch.rgb
                                                holder.cardView.setCardBackgroundColor(rgb)
                                                holder.titleTextView.setTextColor(getBlackWhiteColor(rgb))
                                            }
                                        }
                                    }
                        }
                    })
        }

        holder!!.titleTextView.text = album.album
    }

}