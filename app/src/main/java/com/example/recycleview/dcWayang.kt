package com.example.recycleview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class dcWayang(
    var foto: String,
    var nama: String,
    var karakter: String,
    var deskripsi: String
): Parcelable