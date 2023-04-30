package com.example.artify.ui.Common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artify.R
import com.example.artify.data.enum.ImageLoading
import com.example.artify.ui.loading.SimpleArcRotation
import com.example.artify.ui.theme.Shapes
import com.example.artify.ui.theme.cardImageHeight
import com.example.artify.ui.theme.loadingImageSize

@Preview
@Composable
fun LoadingImage() {
    Box(
        modifier = Modifier
            .size(loadingImageSize)
            .background(MaterialTheme.colors.background, Shapes.medium),
        contentAlignment = Alignment.Center,

        ) {
        SimpleArcRotation()
    }
}


@Composable
fun CustomImage(
    imageUrl: String,
    modifier: Modifier
) {
    val brokenImage = painterResource(id = R.drawable.ic_image_broken)
    Box {
        val imageState = remember {
            mutableStateOf(ImageLoading.LOADING)
        }
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(700)
                .build(),
            contentDescription = "",
            onLoading = {
                imageState.value = ImageLoading.LOADING
            },
            onError = {
                imageState.value = ImageLoading.ERROR
            },
            onSuccess = {
                imageState.value = ImageLoading.SUCCESS
            }
        )
        if (imageState.value == ImageLoading.LOADING) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight)
            ) {
                SimpleArcRotation()
            }
        }
        if (imageState.value == ImageLoading.ERROR) {
            Image(
                painter = brokenImage, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight)
                    .clip(
                        Shapes.medium
                    )
            )
        }
    }
}