package com.babysloth.memo.ui.view.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babysloth.memo.MemoApplication
import com.babysloth.memo.data.repository.RoomMemoRepository
import com.babysloth.memo.domain.usecase.GetBookmarkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarkViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkUiState())

    //TODO: DI 적용 필요
    private var repository: RoomMemoRepository
    private var bookmarkUseCase: GetBookmarkUseCase

    val uiState = _uiState.asStateFlow()

    init {
        //TODO: DI 적용 필요
        repository = RoomMemoRepository(
            MemoApplication.INSTANCE.database.memoDao()
        )
        bookmarkUseCase = GetBookmarkUseCase(repository)

        viewModelScope.launch {
            bookmarkUseCase.getBookmarks()
                .collect { memos ->
                    _uiState.update {
                        it.copy(memos = memos)
                    }
                }
        }
    }
}
