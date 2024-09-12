package di

import database.AppDatabase
import database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { getDatabaseBuilder(get()) }
}