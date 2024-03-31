package com.ifs21033.whatsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Chat (
    val name: String,
    val icon: Int,
    val desc: String,
) : Parcelable
