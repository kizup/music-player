package ru.kizup.musicplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import ru.kizup.musicplayer.model.pojo.Album
import ru.kizup.musicplayer.utils.OnAlbumClickListener
import ru.kizup.musicplayer.view.albums.AlbumsListFragment
import ru.kizup.musicplayer.view.albumsongs.AlbumSongsFragment

class MainActivity : AppCompatActivity(),
        OnAlbumClickListener {

    private val readStoragePermissionReqCode = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        readStoragePermissionReqCode)
            } else {
                showMainFragment()
            }
        }
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, AlbumsListFragment())
                .commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (permissions.size == 1
                && permissions.first() == Manifest.permission.READ_EXTERNAL_STORAGE
                && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            showMainFragment()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onAlbumClick(album: Album) {
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, AlbumSongsFragment.newInstance(album))
                .commit()
    }

}