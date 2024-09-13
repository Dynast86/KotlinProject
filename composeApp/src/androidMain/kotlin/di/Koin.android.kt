package di

import data.db.AppDatabase
import db.createRoomDatabase
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { createRoomDatabase(get()) }
}