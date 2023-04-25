package com.babysloth.memo.domain.usecase

import com.babysloth.memo.data.model.toMemo
import com.babysloth.memo.domain.model.Memo
import com.babysloth.memo.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBookmarkUseCase @Inject constructor(
    private val repository: MemoRepository
) {
    fun getBookmarks(): Flow<List<Memo>> {
        return repository.getBookmarkMemos().map { it.map { memo -> memo.toMemo() } }
    }
}
