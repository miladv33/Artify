package com.example.artify.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.artify.presentation.DetailViewModel
import com.example.artify.ui.common.CustomImage
import com.example.artify.ui.error.Dialog
import com.example.artify.ui.loading.ArcRotationAnimation
import com.example.artify.ui.theme.MainImageSize
import com.example.artify.ui.theme.defaultSpacing
import com.example.artify.ui.theme.loadingImageSize
import com.example.artify.ui.theme.smallSpacing

@Composable
fun DetailScreen(objectID: Int, detailViewModel: DetailViewModel = hiltViewModel()) {
    val loadingData = detailViewModel.getLoadingLiveData().observeAsState()
    if (loadingData.value == true) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    val errorState = detailViewModel.getErrorDialogState().observeAsState()
    if (errorState.value == true) {
        Dialog(detailViewModel, detailViewModel.getErrorMessage())
    }

    LaunchedEffect(objectID) {
        detailViewModel.getObject(objectID)
    }
    if (detailViewModel.metObjectResult.value != null) {

        val data = detailViewModel.metObjectResult.value!!
        val mainImage = remember {
            mutableStateOf(data.primaryImage)
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CustomImage(
                mainImage.value, modifier = Modifier
                    .fillMaxWidth()
                    .size(MainImageSize)
            )
            if (data.additionalImages.isNotEmpty()) {
                LazyRow(modifier = Modifier.padding(defaultSpacing)) {
                    item {
                        CustomImage(
                            data.primaryImage, modifier = Modifier
                                .size(loadingImageSize)
                                .padding(smallSpacing)
                                .clickable {
                                    mainImage.value = data.primaryImage
                                }
                        )
                    }
                    items(data.additionalImages) { imageUrl ->
                        CustomImage(
                            imageUrl, modifier = Modifier
                                .size(loadingImageSize)
                                .padding(smallSpacing)
                                .clickable {
                                    mainImage.value = imageUrl
                                }
                        )
                    }
                }
            }
            Text(
                text = data.title,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(defaultSpacing)
            )
            Text(
                text = data.artistDisplayName,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(defaultSpacing)
            )
            Text(
                text = data.objectDate,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(defaultSpacing)
            )
            Text(
                text = data.department,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(defaultSpacing)
            )
            Text(
                text = data.objectName,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(defaultSpacing)
            )
        }
    }
}
