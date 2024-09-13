package db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.db.AppDatabase
import data.db.dbFileName
import kotlinx.coroutines.Dispatchers

fun createRoomDatabase(ctx: Context): AppDatabase {
    val dbFile = ctx.getDatabasePath(dbFileName)
    return Room.databaseBuilder<AppDatabase>(ctx, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
