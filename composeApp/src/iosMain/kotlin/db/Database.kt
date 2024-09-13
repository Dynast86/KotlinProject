package db

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.db.AppDatabase
import data.db.dbFileName
import data.db.instantiateImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun createRoomDatabase(): AppDatabase {
    val dbFile = "${fileDirectory()}/$dbFileName"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile,
        factory =  { AppDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
