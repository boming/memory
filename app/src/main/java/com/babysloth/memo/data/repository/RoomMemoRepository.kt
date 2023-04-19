package com.babysloth.memo.data.repository

import com.babysloth.memo.data.model.Memo
import com.babysloth.memo.data.room.dao.MemoDao
import com.babysloth.memo.data.room.entity.MemoEntity
import com.babysloth.memo.data.room.entity.toMemo
import com.babysloth.memo.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomMemoRepository(private val memoDao: MemoDao) : MemoRepository {
    override suspend fun create(memo: Memo) {
        memoDao.insertMemo(MemoEntity.from(memo))
    }

    override suspend fun update(memo: Memo) {
        memoDao.updateMemo(MemoEntity.from(memo))
    }

    override suspend fun delete(memo: Memo) {
        memoDao.deleteMemo(MemoEntity.from(memo))
    }

    override fun getMemo(id: Long): Flow<Memo> {
        return memoDao.getMemoById(id)
            .map { it.toMemo() }
    }

    override fun getMemos(): Flow<List<Memo>> {
        return memoDao.getMemos()
            .map { it.map { entity -> entity.toMemo() } }
    }
}
