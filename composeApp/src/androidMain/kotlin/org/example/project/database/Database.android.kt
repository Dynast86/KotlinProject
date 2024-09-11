package org.example.project.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.java.KoinJavaComponent

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext : Context = KoinJavaComponent.getKoin().get()
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
