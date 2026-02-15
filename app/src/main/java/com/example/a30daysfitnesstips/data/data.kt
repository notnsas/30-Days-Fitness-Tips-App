package com.example.a30daysfitnesstips.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a30daysfitnesstips.R

/**
 * A data class to represent the information presented in the dog card
 */
data class Tip(
    @DrawableRes val imageResourceId: Int,
    @StringRes val tipText: Int,
)

val tips = listOf(
    Tip(R.drawable.image_1, R.string.tips_1),
    Tip(R.drawable.image_2, R.string.tips_2),
    Tip(R.drawable.image_3, R.string.tips_3),
    Tip(R.drawable.image_4, R.string.tips_4),
    Tip(R.drawable.image_5, R.string.tips_5),
    Tip(R.drawable.image_6, R.string.tips_6),
    Tip(R.drawable.image_7, R.string.tips_7),
    Tip(R.drawable.image_8, R.string.tips_8),
    Tip(R.drawable.image_9, R.string.tips_9),
    Tip(R.drawable.image_10, R.string.tips_10),
    Tip(R.drawable.image_11, R.string.tips_11),
    Tip(R.drawable.image_12, R.string.tips_12),
    Tip(R.drawable.image_13, R.string.tips_13),
    Tip(R.drawable.image_14, R.string.tips_14),
    Tip(R.drawable.image_15, R.string.tips_15),
    Tip(R.drawable.image_16, R.string.tips_16),
    Tip(R.drawable.image_17, R.string.tips_17),
    Tip(R.drawable.image_18, R.string.tips_18),
    Tip(R.drawable.image_19, R.string.tips_19),
    Tip(R.drawable.image_20, R.string.tips_20),
    Tip(R.drawable.image_21, R.string.tips_21),
    Tip(R.drawable.image_22, R.string.tips_22),
    Tip(R.drawable.image_23, R.string.tips_23),
    Tip(R.drawable.image_24, R.string.tips_24),
    Tip(R.drawable.image_25, R.string.tips_25),
    Tip(R.drawable.image_26, R.string.tips_26),
    Tip(R.drawable.image_27, R.string.tips_27),
    Tip(R.drawable.image_28, R.string.tips_28),
    Tip(R.drawable.image_29, R.string.tips_29),
    Tip(R.drawable.image_30, R.string.tips_30)
)
