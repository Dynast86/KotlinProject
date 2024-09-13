package data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.db.dao.LoginDao
import data.db.entity.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal const val dbFileName = "my_room.data.db"

@Database(entities = [LoginEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() , DB{
    abstract fun getLoginDao(): LoginDao
    override fun clearAllTables() {}
}

interface DB {
    fun clearAllTables()
}


fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
//        .addMigrations(MIGRATIONS)
//        .fallbackToDestructiveMigrationOnDowngrade()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
