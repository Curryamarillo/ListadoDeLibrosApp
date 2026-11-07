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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.listadodelibrosapp.models.Book
import com.example.listadodelibrosapp.viewmodel.BookViewModel

@Composable
fun BookInfo(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    val book: Book by bookViewModel.selectedBook.observeAsState(Book())

    Scaffold(
        modifier = Modifier.fillMaxSize()

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                    navController.popBackStack()
                },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .size(30.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    fontSize = 25.sp,
                    text = "Volver")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(start = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    // Cambio de icono
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
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
                modifier = Modifier.padding(6.dp),
                text = book.author,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier
                    .padding(6.dp)
                    .clickable{
                        // Función del viewmodel que cambia si es o no favorito
                        bookViewModel.toggleFavorite()
                    },
                // If para que se muestre si está seleccionado o no como favorito
                text = if (!book.favorite) "Marcar favorito" else "Quitar favorito",
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
}