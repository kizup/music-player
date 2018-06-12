package ru.kizup.musicplayer.view.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kizup.musicplayer.utils.OnItemClickListener

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
abstract class BaseAdapter<Item : Any, Holder : BaseViewHolder> : RecyclerView.Adapter<Holder>() {

    var listener: OnItemClickListener<Item>? = null
    protected val items = mutableListOf<Item>()
    private var context: Context? = null

    protected fun inflate(parent: ViewGroup, id: Int): View = LayoutInflater.from(parent.context).inflate(id, parent, false)

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<Item>, needNotify: Boolean = true) {
        this.items.clear()
        this.items.addAll(items)
        if (needNotify) {
            notifyDataSetChanged()
        }
    }

}