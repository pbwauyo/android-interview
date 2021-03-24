package com.peter.androidinterview.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geo (
    val lat: String,
    val long: String
    ) : Parcelable