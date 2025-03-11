package com.example.composepokedex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.example.composepokedex.R

val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)

val RobotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_light, FontWeight.Light),
    Font(R.font.roboto_condensed_regular, FontWeight.Normal),
)
val Poppins = FontFamily(
    Font(R.font.poppins,FontWeight.Normal),
    Font(R.font.poppins_bold,FontWeight.Bold),
    Font(R.font.poppins_light,FontWeight.Light),
    Font(R.font.poppins_semibold,FontWeight.SemiBold),
    Font(R.font.poppins_medium,FontWeight.Medium),

)

val Headline = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    lineHeight = 32.sp,
    fontSize = 23.sp
)
val Subtitle3 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    lineHeight = 16.sp,
    fontSize = 10.sp
)
val Subtitle1 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    lineHeight = 16.sp,
    fontSize = 14.sp
)
val subtitle2 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    lineHeight = 16.sp,
    fontSize = 12.sp
)
val Body3 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp,
    fontSize = 10.sp
)
val Body1 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp,
    fontSize = 12.sp
)
val Body2 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp,
    fontSize = 12.sp
)


val Caption = TextStyle(
    textAlign = TextAlign.Unspecified,
    textDirection = TextDirection.Unspecified,
    lineBreak = LineBreak.Simple,
    hyphens = Hyphens.Auto,
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp,
    fontSize = 8.sp,
    color = Medium

)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)