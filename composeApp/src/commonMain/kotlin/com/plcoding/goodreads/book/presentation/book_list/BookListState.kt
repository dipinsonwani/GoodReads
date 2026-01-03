package com.plcoding.goodreads.book.presentation.book_list

import com.plcoding.goodreads.book.domain.Book
import com.plcoding.goodreads.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Murakami",
    val searchResults: List<Book> = emptyList(),
    val favouriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)