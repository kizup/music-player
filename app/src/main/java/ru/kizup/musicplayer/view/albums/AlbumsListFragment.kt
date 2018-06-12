package ru.kizup.musicplayer.view.albums

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_album_songs.*
import kotlinx.android.synthetic.main.fragment_albums.*
import org.koin.android.ext.android.inject
import ru.kizup.musicplayer.R
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.utils.OnAlbumClickListener
import ru.kizup.musicplayer.utils.OnItemClickListener
import ru.kizup.musicplayer.view.base.BaseFragment
import ru.kizup.musicplayer.viewmodel.albums.AlbumsViewModel

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
class AlbumsListFragment : BaseFragment(), OnItemClickListener<Album> {

    private val adapter: AlbumsAdapter by inject()
    private val albumsViewModel: AlbumsViewModel by inject()
    private lateinit var onAlbumClickListener: OnAlbumClickListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onAlbumClickListener = context as OnAlbumClickListener
    }

    override val layoutId: Int
        get() = R.layout.fragment_albums

    override fun onPostViewCreated() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        rvAlbums.layoutManager = GridLayoutManager(activity, 2)
        rvAlbums.setHasFixedSize(true)
        rvAlbums.adapter = adapter
        adapter.listener = this

        adapter.setItems(albumsViewModel.getAlbums())
    }

    override fun onItemClick(item: Album) {
        onAlbumClickListener.onAlbumClick(item)
    }

}