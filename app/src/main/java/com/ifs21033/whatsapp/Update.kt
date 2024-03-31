package com.ifs21033.whatsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Update (
    val icon: Int,
) : Parcelable