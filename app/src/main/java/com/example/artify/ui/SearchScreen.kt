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
import com.example.artify.ui.Common.ErrorIndicator
import com.example.artify.ui.Common.LoadingIndicator
import com.example.artify.ui.theme.*
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(searchViewModel: SearchViewModel = hiltViewModel(), onItemClick: (Int) -> Unit) {
    LoadingIndicator(searchViewModel)
    ErrorIndicator(searchViewModel)
    Column {
        val searchedKey = remember {
            mutableStateOf(searchViewModel.lastSearchQuery)
        }
        SearchInput {
            searchedKey.value = it
            searchViewModel.clearResults()
            searchViewModel.search(it)
        }

        Spacer(modifier = Modifier.size(2.dp))
        ListOfNumbers(query = searchedKey.value, onItemClick)
    }
}

@ExperimentalFoundationApi
@Composable
fun ListOfNumbers(
    query: String,
    onItemClick: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchResult by viewModel.searchResult.observeAsState()
    LazyVerticalGrid(
        GridCells.Fixed(3)
    ) {
        itemsIndexed(
            searchResult ?: emptyList()
        ) { index, item ->
            NumberCard(item, onItemClick)
        }
        if (!viewModel.searchResult.value.isNullOrEmpty() && viewModel.hasMoreData.value == true)
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
                    delay(150)
                    viewModel.search(query)
                }
            }
    }
}

@Composable
fun NumberCard(number: Int, onItemClick: (Int) -> Unit) {
    Box(modifier = Modifier.clickable {
        onItemClick.invoke(number)
    }) {
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
    val shape = Shapes.medium

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