package com.babysloth.memo.ui.view.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babysloth.memo.MemoApplication
import com.babysloth.memo.data.database.MemoRoomDatabase
import com.babysloth.memo.data.repository.RoomMemoRepository
import com.babysloth.memo.domain.usecase.GetBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    database: MemoRoomDatabase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkUiState())

    private var repository = RoomMemoRepository(database.memoDao())
    private var bookmarkUseCase = GetBookmarkUseCase(repository)

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
