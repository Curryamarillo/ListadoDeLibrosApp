package com.example.listadodelibrosapp.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lopez_jorge.viewmodel_libreria_t5.models.Book
import com.lopez_jorge.viewmodel_libreria_t5.viewmodel.BookViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    val books: List<Book> by bookViewModel.books.observeAsState(initial = emptyList())
    val isLoadingBooks: Boolean by bookViewModel.isLoading.observeAsState(initial = false)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 8.dp)
    ) {
        items(books) { book ->
            BookCard(book = book, navController = navController, bookViewModel = bookViewModel)
        }
    }

    if (isLoadingBooks) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
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
    }
}

@Composable
fun BookInfo(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    val book: Book by bookViewModel.selectedBook.observeAsState(Book())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver"
            )
            Text(text = "Volver")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = "book"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = book.title,
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = book.author,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(8.dp)
        ) {
            Text(
                text = "Aquí se mostrará la información detallada del libro.",
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}