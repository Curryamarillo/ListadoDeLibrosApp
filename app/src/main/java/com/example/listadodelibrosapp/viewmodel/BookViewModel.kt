package com.example.listadodelibrosapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadodelibrosapp.models.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class BookViewModel : ViewModel() {
    // Los LiveData (estados) solo deben de poder cambiar desde el View Model, por ello se declaran private.
    // Para acceder al valor de los estados desde el exterior del View Model se crea una variable
    //  no mutable que almacenará el mismo valor que la variable privada

    // Lista de libros
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    // Libro seleccionado
    private val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book> = _selectedBook

    // Variable para indicar que se están obteniendo los datos del repositorio
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Función toggle para favorito
    fun toggleFavorite() {
        // Si es nulo sale de la funcion y no hace nada
        val current = _selectedBook.value ?: return
        // Crea un nuevo objeto cambiando la propiedad favorite
        // Invierte el booleano
        val updated = current.copy(favorite = !current.favorite)
        // Reemplaza el objeto por uno nuevo actualizado
        _selectedBook.value = updated
        // Actualiza mapeando la lista incluyendo el objeto de antes con su
        // valor actualizado
        _books.value = _books.value?.map { if (it == current) updated else it }
    }

    // Cuando instancia un objeto BookViewModel tras llamar al constructor se ejecuta el bloque init
    init {
        // Corrutina: coroutineScope
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000.milliseconds)
            _books.value = Book.getData()
            _isLoading.value = false
        }
    }

    fun deleteBook(book: Book) {
        // Con API o BBDD se mandaría el id y se borraría.
        //  Por último se obtendría la lista actualizada y se actualizaría el LiveData

        // En este caso se elimina el libro de la lista y se actualiza el LiveData
        _books.value = _books.value?.filter { it != book }
    }

    // Al pulsar sobre un libro se almacena como seleccionado.
    fun onBookClicked(book: Book) {
        _selectedBook.value = book
    }
}