package com.example.artify.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.artify.presentation.error.ShowErrorDelegate
import com.example.artify.presentation.loading.ILoading
import com.example.artify.ui.error.Dialog
import com.example.artify.ui.loading.ArcRotationAnimation

@Composable
fun ErrorIndicator(showErrorDelegate: ShowErrorDelegate) {
    val errorState = showErrorDelegate.getErrorDialogState().observeAsState()
    if (errorState.value == true) {
        Dialog(showErrorDelegate, showErrorDelegate.getErrorMessage())
    }
}

@Composable
fun LoadingIndicator(loadingDelegate: ILoading) {
    val loadingData = loadingDelegate.getLoadingLiveData().observeAsState()
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
}
