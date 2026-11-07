package com.example.listadodelibrosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.listadodelibrosapp.R
import com.example.listadodelibrosapp.models.Book
import com.example.listadodelibrosapp.navigation.Routes
import com.example.listadodelibrosapp.viewmodel.BookViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    val books: List<Book> by bookViewModel.books.observeAsState(initial = emptyList())
    val isLoadingBooks: Boolean by bookViewModel.isLoading.observeAsState(initial = false)

    // Scaffold que envuelve el column original para poder ubicar el bottom bar con la foto y nombres
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        bottomBar = {
            // Se enseña si no está cargando
            if(!isLoadingBooks) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(76.dp)
                            .clip(
                                shape = CircleShape
                            ),
                        painter = painterResource(R.drawable.simpson_yo),
                        contentDescription = "Gustavo Starace",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = "Gustavo Starace",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 30.sp
                    )
                }
            }

        }
    ) { innerPadding ->
        if (isLoadingBooks) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Loading...",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(innerPadding)
            ) {
                items(books) { book ->
                    BookCard(book = book, navController = navController, bookViewModel = bookViewModel)
                }

            }
        }
    }

}

@Composable
fun BookCard(
    book: Book,
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    OutlinedCard(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                bookViewModel.onBookClicked(book)
                navController.navigate(Routes.BookInfo.route)
            }
    ) {
        ListItem(
            modifier = Modifier.padding(4.dp),
            headlineContent = { Text(text = book.title) },
            supportingContent = { Text(text = book.author) },
            leadingContent = {
                if (book.favorite) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "book",
                        tint = Color(0xFFFB8C00)
                    )
                }
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "book",
                    modifier = Modifier.clickable {
                        bookViewModel.deleteBook(book)
                    }
                )
            }
        )
    }
}
