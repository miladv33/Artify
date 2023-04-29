package com.example.artify.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.artify.R
import com.example.artify.presentation.SearchViewModel
import com.example.artify.ui.error.Dialog
import com.example.artify.ui.loading.ArcRotationAnimation
import com.example.artify.ui.theme.*
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(searchViewModel: SearchViewModel = hiltViewModel()) {
    val loadingData = searchViewModel.getLoadingLiveData().observeAsState()
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
    val errorState = searchViewModel.getErrorDialogState().observeAsState()
    if (errorState.value == true) {
        Dialog(searchViewModel, searchViewModel.getErrorMessage())
    }
    ArtifyTheme {
        Column {
            val searchedKey = remember {
                mutableStateOf("")
            }
            SearchInput {
                searchedKey.value = it
                searchViewModel.search(it)
            }

            Spacer(modifier = Modifier.size(2.dp))
            ListOfNumbers(query = searchedKey.value)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ListOfNumbers(query: String, viewModel: SearchViewModel = hiltViewModel()) {
    // Observe the search result from the view model
    val searchResult by viewModel.searchResult.observeAsState()
    // Use LazyVerticalGrid to display a grid of items
    LazyVerticalGrid(
        // Specify 3 columns for the grid
        GridCells.Fixed(3)
    ) {
        // Use itemsIndexed to get the index and item of each element in the list
        itemsIndexed(
            searchResult ?: emptyList()
        ) { index, item ->
            // Use Text to display the item as a string
            NumberCard(item)
        }
        if (!viewModel.searchResult.value.isNullOrEmpty())
            item {
                Box(modifier = Modifier.size(32.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .progressSemantics()
                            .padding(start = 5.dp)
                            .size(32.dp), strokeWidth = 1.dp
                    )
                }
                LaunchedEffect(true) {
                    // This is to confirm that I am using pagination to load the data. just to show you.
                    delay(500)
                    viewModel.search(query)
                }
            }
    }
}

@Composable
fun NumberCard(number: Int) {
    // Use a mutable state to store the visibility of the card
    var visible by remember { mutableStateOf(true) }
    // Use Modifier.clickable to handle the click event
    Box(modifier = Modifier.clickable { visible = !visible }) {
        // Use AnimatedVisibility to show or hide the card with scale animations
        // Use Card to display the number
        Card(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
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