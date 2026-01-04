package com.plcoding.goodreads.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.favourites
import cmp_bookpedia.composeapp.generated.resources.search_results
import com.plcoding.goodreads.book.domain.Book
import com.plcoding.goodreads.book.presentation.book_list.components.BookSearchBar
import com.plcoding.goodreads.core.presentation.DarkBlue
import com.plcoding.goodreads.core.presentation.DesertWhite
import com.plcoding.goodreads.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BookListScreen(
    viewModel: BookListViewModel,
    onBookClick: (Book) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListContent(state = state, onAction = viewModel::onAction)
}


@Composable
private fun BookListContent(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    contentColor = SandYellow,
                    indicator = {
                        tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier.tabIndicatorOffset(
                                tabPositions[state.selectedTabIndex]
                            )
                        )
                    }

                ) {

                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        selectedContentColor = SandYellow,
                        modifier = Modifier.weight(1f),
                        unselectedContentColor = Color.Black.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }

                    Tab(
                        selected = state.selectedTabIndex == 1,
                        selectedContentColor = SandYellow,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        unselectedContentColor = Color.Black.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favourites),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }

}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        description = "Description $it",
        authors = listOf("Dipin Sonwani"),
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 3.6784,
        ratingCount = 0,
        numPages = 100,
        numEditions = 1
    )
}

@Preview
@Composable
private fun BookListContentPreview(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
) {

    BookListContent(
        state = BookListState(
            searchResults = books,
        ),
        onAction = {}
    )

}
