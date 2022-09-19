package com.example.bodymapkt

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//classe de estrutura de dados
data class MainItem(
    val id: Int,
    @DrawableRes val drawbleId: Int,
    @StringRes val textStringId: Int,
    val color: Int
)
