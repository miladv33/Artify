package com.example.artify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.artify.presentation.SearchViewModel
import com.example.artify.ui.theme.ArtifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TestToSee()
                }
            }
        }
    }
}


@Composable
fun TestToSee(searchViewModel: SearchViewModel = hiltViewModel()) {
    if (searchViewModel.searchResult.value == null) {
        searchViewModel.search("Iran")
    }
    val lazyPagingItems = searchViewModel.searchResult.asFlow().collectAsLazyPagingItems()
    LazyColumn {
        items(lazyPagingItems) { item ->
            Text(text = item.toString(), color = Color.Red)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtifyTheme {
        Greeting("Android")
    }
}