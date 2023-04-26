package com.example.artify.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.artify.ui.theme.FONT_SIZE_CAPTION
import com.example.artify.ui.theme.FONT_SIZE_H5
import com.example.artify.ui.theme.FONT_SIZE_SUBTITLE1

// Set of Material typography styles to start with
val Typography = Typography(
    h5 = TextStyle(
        fontSize = FONT_SIZE_H5,
        fontWeight = FontWeight.Bold
    ),
    subtitle1 = TextStyle(
        fontSize = FONT_SIZE_SUBTITLE1,
        fontWeight = FontWeight.Normal
    ),
    caption = TextStyle(
        fontSize = FONT_SIZE_CAPTION,
        fontWeight = FontWeight.Normal
    )
)
