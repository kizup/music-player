package ru.kizup.musicplayer.view.albumsongs

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_songs.*
import org.koin.android.ext.android.inject
import ru.kizup.musicplayer.R
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.model.pojo.Song
import ru.kizup.musicplayer.utils.OnItemClickListener
import ru.kizup.musicplayer.utils.getStatusBarColor
import ru.kizup.musicplayer.view.base.BaseFragment
import ru.kizup.musicplayer.view.base.SongsAdapter
import ru.kizup.musicplayer.viewmodel.albums.songs.AlbumSongsViewModel
import java.lang.Exception

/**
 * Created by: kizup on 10.06.18.
 * Skype: kizupx
 */
class AlbumSongsFragment : BaseFragment(), OnItemClickListener<Song> {

    companion object {

        const val ALBUM_ARG = "album_arg"

        fun newInstance(album: Album): AlbumSongsFragment {
            val fragment = AlbumSongsFragment()
            val args = Bundle()
            args.putParcelable(ALBUM_ARG, album)
            fragment.arguments = args
            return fragment
        }
    }

    private val adapter: SongsAdapter by inject()
    private val albumSongsViewModel: AlbumSongsViewModel by inject()
    private val picasso by inject<Picasso>()
    private var primaryColor: Int = -1

    override val layoutId: Int
        get() = R.layout.fragment_album_songs

    override fun onPostViewCreated() {
        val album: Album = arguments.getParcelable(ALBUM_ARG)
        albumSongsViewModel.album = album
        collapsingToolbar.isTitleEnabled = true
        collapsingToolbar.title = album.album

        parentActivity.setSupportActionBar(toolbar)
        parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter.listener = this
        rvAlbumSongs.layoutManager = LinearLayoutManager(activity)
        rvAlbumSongs.setHasFixedSize(true)
        rvAlbumSongs.adapter = adapter
        rvAlbumSongs.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter.setItems(albumSongsViewModel.getAlbumSongs())
        picasso.load(album.artUri)
                .placeholder(R.drawable.ic_library_music)
                .into(ivAlbumArt, object : Callback {
                    override fun onError(e: Exception?) {}

                    override fun onSuccess() {
                        val bd = ivAlbumArt.drawable as BitmapDrawable
                        Palette.Builder(bd.bitmap)
                                .generate {
                                    val swatch = it.vibrantSwatch
                                    val window = activity.window
                                    if (swatch != null) {
                                        primaryColor = swatch.rgb
                                    } else {
                                        val mutedSwatch = it.mutedSwatch
                                        if (mutedSwatch != null) {
                                            primaryColor = mutedSwatch.rgb
                                        }
                                    }

                                    collapsingToolbar.setContentScrimColor(primaryColor)
                                    window.statusBarColor = getStatusBarColor(primaryColor)
                                }
                    }
                })
    }

    override fun onItemClick(item: Song) {
    }

}