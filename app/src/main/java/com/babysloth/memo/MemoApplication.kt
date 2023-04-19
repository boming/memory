package com.babysloth.memo

import android.app.Application
import com.babysloth.memo.data.database.MemoRoomDatabase

class MemoApplication : Application() {
    lateinit var database: MemoRoomDatabase

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        database = MemoRoomDatabase.getDatabase(context = this)
    }

    companion object {
        lateinit var INSTANCE: MemoApplication
            private set
    }
}
