package com.example.listadodelibrosapp.models

data class Book(
    val title: String = "",
    val author: String = "",
    var favorite: Boolean = false,
    var visible: Boolean = true,
) {
    companion object {
        fun getData(): List<Book> {
            return listOf(
                Book("Ready Player One", "Ernest Cline"),
                Book("El juego de Ender", "Orson Scott Card"),
                Book("El señor de los anillos", "J. R. R. Tolkien"),
                Book("La historia interminable", "Michael Ende"),
                Book("Juego de tronos", "George R. R. Martin"),
                Book("El color de la magia", "Terry Pratchett"),
                Book("La sangre de los elfos", "Andrzej Sapkowski"),
                Book("Dune", "Frank Herbert"),
                Book("Una educación mortal: Primera lección de Escolomancia", "Naomi Novik"),
                Book("El nombre del viento", "Patrick Rothfuss"),
                Book("Harry Potter y la piedra filosofal", "J. K. Rowling"),
                Book("La quinta ola", "Rick Yancey"),
                Book("Las crónicas de Narnia", "C. S. Lewis"),
            )
        }
    }
}

