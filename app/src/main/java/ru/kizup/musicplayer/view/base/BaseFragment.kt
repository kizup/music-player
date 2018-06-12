package ru.kizup.musicplayer.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int
    protected abstract fun onPostViewCreated()

    protected lateinit var parentActivity: AppCompatActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parentActivity = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater?.inflate(layoutId, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onPostViewCreated()
    }

}