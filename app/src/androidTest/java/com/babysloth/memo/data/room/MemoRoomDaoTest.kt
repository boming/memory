package com.babysloth.memo.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.babysloth.memo.data.database.MemoRoomDatabase
import com.babysloth.memo.data.room.dao.MemoDao
import com.babysloth.memo.data.room.entity.MemoEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MemoRoomDaoTest {
    private lateinit var dao: MemoDao
    private lateinit var roomMemoDatabase: MemoRoomDatabase

    private val memo1 = MemoEntity(
        id = 1,
        createdAt = 0,
        updatedAt = 1,
        title = "",
        description = "",
        isBookmark = false
    )
    private val memo2 = MemoEntity(
        id = 2,
        createdAt = 0,
        updatedAt = 2,
        title = "",
        description = "",
        isBookmark = false
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        roomMemoDatabase =
            Room.inMemoryDatabaseBuilder(context, MemoRoomDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries().build()
        dao = roomMemoDatabase.memoDao()
    }

    @Test
    fun daoInsert_insertMemoToDb() = runBlocking {
        dao.insertMemo(memo1)

        val memo = dao.getMemoById(1).first()

        assertEquals(memo1.id, memo.id)
    }

    @Test
    fun daoInsert_insertMemosToDb() = runBlocking {
        dao.insertMemo(memo1)
        dao.insertMemo(memo2)

        val memos = dao.getMemos().first()

        assertEquals(memo2.id, memos[0].id)
        assertEquals(memo1.id, memos[1].id)
    }

    @Test
    fun daoUpdate_updateMemo() = runBlocking {
        dao.insertMemo(memo1)

        dao.updateMemo(
            memo1.copy(description = "description")
        )

        val memo = dao.getMemoById(1).first()

        assertEquals("description", memo.description)
    }

    @Test
    fun daoDelete_deleteMemo() = runBlocking {
        dao.insertMemo(memo1)
        dao.insertMemo(memo2)

        dao.deleteMemo(memo2)

        val memos = dao.getMemos().first()

        assertEquals(1, memos.size)
    }

    @After
    fun closeDb() {
        roomMemoDatabase.close()
    }
}
