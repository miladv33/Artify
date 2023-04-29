package com.example.artify.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.artify.R
import com.example.artify.presentation.SearchViewModel
import com.example.artify.ui.theme.*


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun Home(searchViewModel: SearchViewModel = hiltViewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current

    ArtifyTheme {
        Column {

            val lazyPagingItems = searchViewModel.searchResult
            SearchInput {
                searchViewModel.search("Iran")
            }

            Spacer(modifier = Modifier.size(2.dp))
            ListOfNumbers2(query = "Iran")

//            val listState = rememberLazyListState()
//
//            LazyColumn(state = listState) {
//                items(lazyPagingItems.value ?: arrayListOf()) { item ->
//                    Text(text = item.toString(), color = Color.Red)
//                }
//                item {
//                    LaunchedEffect(true) {
//                        searchViewModel.search("Iran")
//                        //Do something when List end has been reached
//                    }
//                }
//            }

        }


    }
}

@Composable
fun ListOfNumbers(query: String, viewModel: SearchViewModel = hiltViewModel()) {
    // Observe the viewModel.searchResult as a state
    val observeAsState by viewModel.searchResult.observeAsState()

    // Launch an effect when the query changes
    LaunchedEffect(query) {
        viewModel.search(query)
    }

    // Display the items using LazyColumn
    LazyColumn {
        // Use observeAsState.value as the parameter for items()
        items(observeAsState as List<Int>) { item ->
            Text(text = item.toString())
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun ListOfNumbers2(query: String, viewModel: SearchViewModel = hiltViewModel()) {
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
        item {
            CircularProgressIndicator(modifier = Modifier.size(12.dp))
            LaunchedEffect(true) {
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
            Text(
                text = number.toString(),
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
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