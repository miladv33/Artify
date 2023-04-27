package com.example.artify.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.artify.R
import com.example.artify.presentation.SearchViewModel
import com.example.artify.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Home(searchViewModel: SearchViewModel = hiltViewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current

    ArtifyTheme{
        Column {

            val lazyPagingItems = searchViewModel.searchResult.observeAsState()
            SearchInput {
                searchViewModel.search("Iran")
            }

//            when {
//                lazyPagingItems.loadState.refresh is LoadState.Loading -> {
//                    // Show loading progress bar
//                }
//                lazyPagingItems.loadState.append is LoadState.Loading -> {
//                    // Show loading progress bar at the end of the list
//                }
//                lazyPagingItems.loadState.refresh is LoadState.Error -> {
//                    // Show error message and retry button
//                    val error = lazyPagingItems.loadState.refresh as LoadState.Error
//                    Button(onClick = { lazyPagingItems.retry() }) {
//                        Text(text = "Retry")
//                    }
//                    Text(text = error.error.message ?: "Unknown error")
//                }
//                lazyPagingItems.loadState.append is LoadState.Error -> {
//                    // Show error message and retry button at the end of the list
//                    val error = lazyPagingItems.loadState.append as LoadState.Error
//                    Button(onClick = { lazyPagingItems.retry() }) {
//                        Text(text = "Retry")
//                    }
//                    Text(text = error.error.message ?: "Unknown error")
//                }
//                else->{
//
//                }
//
//            }

            Spacer(modifier = Modifier.size(2.dp))
            LazyColumn {
                items(lazyPagingItems.value?.objectIDs?.size ?: 0) {
                    lazyPagingItems.value?.objectIDs?.get(it)
                        ?.let { it1 -> Text(text = it1.toString(), color = Color.Red) }
                }
            }
        }
    }
}

@Composable
fun SearchInput(
    onSearchClick: (value: String) -> Unit
) {
    // Define a shape for the input text with rounded corners
    val shape = Shapes.medium

    // Define and remember a mutable state for the search value
    val searchValue = remember { mutableStateOf("") }

    val modifier = Modifier
        .padding(defaultSpacing)
        .background(MaterialTheme.colors.secondary, shape)

    Row(
        modifier = modifier.border(
            width = borderSize, color = MaterialTheme.colors.primary, shape = shape
        ), verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchValue.value,
            label = {
                Text(text = stringResource(R.string.search))
            },
            onValueChange = {
                searchValue.value = it
            },
            modifier = Modifier.weight(1f),
            shape = shape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
        )

        IconButton(
            onClick = {
                onSearchClick.invoke(searchValue.value)
            }, modifier = Modifier.size(defaultIconButtonPadding)
        ) {
            Icon(
                tint = MaterialTheme.colors.primary,
                imageVector = Icons.Default.Search, contentDescription = "Search"
            )
        }
    }
}