package com.babysloth.memo.domain.usecase

import com.babysloth.memo.data.model.toMemo
import com.babysloth.memo.data.repository.RoomMemoRepository
import com.babysloth.memo.domain.model.Memo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBookmarkUseCase(private val repository: RoomMemoRepository) {
    fun getBookmarks(): Flow<List<Memo>> {
        return repository.getBookmarkMemos()
            .map { it.map { memo -> memo.toMemo() } }
    }
}
