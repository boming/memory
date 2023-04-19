package com.babysloth.memo.ui.view.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babysloth.memo.domain.model.Memo

@Composable
fun BookmarkScreen(bookmarkViewModel: BookmarkViewModel = viewModel()) {
    val uiState by bookmarkViewModel.uiState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.memos) { memo ->
            BookmarkItem(memo = memo)
        }
    }
}

@Composable
fun BookmarkItem(memo: Memo, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(100.dp)
    ) {
        Text(text = memo.title)
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        BookmarkItem(memo = Memo(title = "나는 메모 외롭지 않아"))
    }
}
