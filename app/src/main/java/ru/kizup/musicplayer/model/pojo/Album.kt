package ru.kizup.musicplayer.model.pojo

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by: kizup on 09.06.18.
 * Skype: kizupx
 */
data class Album(val album: String,
                 val id: Int,
                 val key: String,
                 var art: String = "",
                 val artUri: Uri?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Uri::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(album)
        parcel.writeInt(id)
        parcel.writeString(key)
        parcel.writeString(art)
        parcel.writeParcelable(artUri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }

}