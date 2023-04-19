package com.babysloth.memo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.babysloth.memo.data.room.dao.MemoDao
import com.babysloth.memo.data.room.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1, exportSchema = true)
abstract class MemoRoomDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object {
        @Volatile
        private var INSTANCE: MemoRoomDatabase? = null

        fun getDatabase(context: Context): MemoRoomDatabase {
            return Room.databaseBuilder(
                context,
                MemoRoomDatabase::class.java,
                "memo_room.db"
            ).fallbackToDestructiveMigration()
                .build().also { INSTANCE = it }
        }
    }
}
