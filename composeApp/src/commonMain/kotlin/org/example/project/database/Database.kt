package org.example.project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.database.dao.LoginDao
import org.example.project.database.entity.LoginEntity

@Database(entities = [LoginEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLoginDao(): LoginDao
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

fun getRoomDatabase(): AppDatabase {
    return getDatabaseBuilder()
//      .addMigrations(MIGRATION_1_2)
//        .fallbackToDestructiveMigrationOnDowngrade()
//        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
