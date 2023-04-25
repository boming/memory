package com.babysloth.memo.ui.view.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babysloth.memo.MemoApplication
import com.babysloth.memo.data.database.MemoRoomDatabase
import com.babysloth.memo.data.repository.RoomMemoRepository
import com.babysloth.memo.data.room.dao.MemoDao
import com.babysloth.memo.data.room.entity.MemoEntity
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
    database: MemoRoomDatabase,
    bookmarkUseCase: GetBookmarkUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkUiState())

    val uiState = _uiState.asStateFlow()
    val dao = database.memoDao()

    init {
        viewModelScope.launch {
            dao.updateMemo(
                MemoEntity(
                    id = 1,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis(),
                    title = "1111111",
                    description = "",
                    isBookmark = true
                )
            )

            dao.updateMemo(
                MemoEntity(
                    id = 3,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis(),
                    title = "222222",
                    description = "",
                    isBookmark = true
                )
            )

            dao.updateMemo(
                MemoEntity(
                    id = 5,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis(),
                    title = "33333",
                    description = "",
                    isBookmark = true
                )
            )
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
