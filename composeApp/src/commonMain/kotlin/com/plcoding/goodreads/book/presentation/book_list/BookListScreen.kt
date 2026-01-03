package com.plcoding.goodreads.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.goodreads.book.domain.Book

@Composable
fun BookListScreen(
    viewModel: BookListViewModel,
    onBookClick: (Book) -> Unit,
    modifier: Modifier ){

    val state by viewModel.state.collectAsStateWithLifecycle()
    
   BookListContent(state = state, onAction = { action ->
       when(action){
           is BookListAction.OnBookClick -> onBookClick(action.book)
           else -> Unit
       }
   })
}



@Composable
private fun BookListContent(
    state: BookListState,
    onAction: (BookListAction) -> Unit,){


}