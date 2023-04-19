package com.babysloth.memo.ui.view.bookmark

import com.babysloth.memo.domain.model.Memo
import kotlinx.coroutines.flow.Flow

data class BookmarkUiState(
    val memos: List<Memo> = emptyList()
)
