package com.babysloth.memo.ui.view.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babysloth.memo.MemoApplication
import com.babysloth.memo.data.repository.RoomMemoRepository
import com.babysloth.memo.domain.usecase.GetBookmarkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarkViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkUiState())

    //TODO: DI 적용 필요
    private var repository: RoomMemoRepository = RoomMemoRepository(
        MemoApplication.INSTANCE.database.memoDao()
    )
    private var bookmarkUseCase: GetBookmarkUseCase = GetBookmarkUseCase(repository)

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bookmarkUseCase.getBookmarks()
                .onStart {
                    _uiState.update {
                        it.copy(result = BookmarkState.Loading)
                    }
                }
                .catch { exception ->
                    _uiState.update {
                        it.copy(result = BookmarkState.Fail(exception.message ?: "실패했습니다."))
                    }
                }
                .collect { memos ->
                    _uiState.update {
                        it.copy(memos = memos, result = BookmarkState.Success)
                    }
                }
        }
    }
}
