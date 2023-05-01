package com.example.artify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.artify.data.model.MetObjectData
import com.example.artify.presentation.DetailViewModel
import com.example.artify.ui.Common.CustomImage
import com.example.artify.ui.error.Dialog
import com.example.artify.ui.loading.ArcRotationAnimation
import com.example.artify.ui.theme.MainImageSize

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
            ArcRotationAnimation()
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

        // Display the detail screen with the data
        val data = detailViewModel.metObjectResult.value!!
        val mainImage = remember {
            mutableStateOf(data.primaryImage)
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            // Display the primary image
            CustomImage(
                mainImage.value, modifier = Modifier
                    .fillMaxWidth()
                    .size(MainImageSize)
            )
            // Display the additional images gallery, if available
            if (data.additionalImages.isNotEmpty()) {
                LazyRow(modifier = Modifier.padding(8.dp)) {
                    item {
                        CustomImage(
                            data.primaryImage, modifier = Modifier
                                .size(120.dp)
                                .padding(4.dp)
                                .clickable {
                                    mainImage.value = data.primaryImage
                                }
                        )
                    }
                    items(data.additionalImages) { imageUrl ->
                        CustomImage(
                            imageUrl, modifier = Modifier
                                .size(120.dp)
                                .padding(4.dp)
                                .clickable {
                                    mainImage.value = imageUrl
                                }
                        )
                    }
                }
            }
            // Display the overview with detailed information
            Text(
                text = data.title,
                color = Color.Red,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = data.artistDisplayName,
                color = Color.Red,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = data.objectDate,
                color = Color.Red,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = data.department,
                color = Color.Red,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = data.objectName,
                color = Color.Red,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun DetailScreen2(objectId: Int) {

}