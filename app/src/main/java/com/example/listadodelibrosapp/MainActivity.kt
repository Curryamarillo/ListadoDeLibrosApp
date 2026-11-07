package com.example.listadodelibrosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.listadodelibrosapp.navigation.Navigation
import com.example.listadodelibrosapp.ui.theme.ListadoDeLibrosAppTheme
import com.example.listadodelibrosapp.viewmodel.BookViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {

    // El ViewModel se declara correctamente a nivel de clase o dentro de onCreate,
    // pero nunca "flotando" entre métodos.
    private val bookViewModel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ListadoDeLibrosAppTheme {
                // A surface container using the 'background' color from the theme
                // Lo he cambiado porque es de color blanco y queda mal
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    Navigation(bookViewModel)
                    // Si solo se necesita el View Model en un lugar se puede declarar al llamar al componente
                    // Navigation(BookViewModel())
                }
            }
        }
    }
}
