package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import data.repository.LoginRepository
import data.repository.SalesReportRepository
import domain.LoginRepositoryImpl
import domain.SalesReportRepositoryImpl
import ui.login.LoginViewModel
import ui.next.NextViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        networkModule,
        repositoryModule,
        viewModelModule,
        platformModule(),
    )
}

private val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url.takeFrom(URLBuilder().takeFrom("http://10.222.27.109"))
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 5_000
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            install(Resources)
        }
    }
}

private val repositoryModule = module {
    singleOf(::LoginRepositoryImpl).bind(LoginRepository::class)
    singleOf(::SalesReportRepositoryImpl).bind(SalesReportRepository::class)
}

private val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::NextViewModel)
}