package com.example.listadodelibrosapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.listadodelibrosapp.models.Book
import com.example.listadodelibrosapp.navigation.Routes
import com.example.listadodelibrosapp.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
                        tint = Color(0xFFFFB8C00)
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