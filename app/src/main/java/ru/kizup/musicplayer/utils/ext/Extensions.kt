package ru.kizup.musicplayer.utils.ext

import android.view.View
import ru.kizup.musicplayer.view.base.BaseAdapter
import ru.kizup.musicplayer.view.base.BaseViewHolder

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */

fun <T: View> BaseViewHolder.findView(id: Int): T = itemView.findViewById(id)