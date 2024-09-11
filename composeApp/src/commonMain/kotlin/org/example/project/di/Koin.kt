package org.example.project.di

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
import org.example.project.data.repository.LoginRepository
import org.example.project.data.repository.SalesReportRepository
import org.example.project.domain.SalesReportRepositoryImpl
import org.example.project.domain.LoginRepositoryImpl
import org.example.project.ui.AppViewModel
import org.example.project.ui.login.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


fun initKoin() = startKoin {
    modules(
        networkModule,
        repositoryModule,
//        useCaseModule,
        viewModelModule
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
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::SalesReportRepositoryImpl).bind<SalesReportRepository>()
}

private val viewModelModule = module {
    single { AppViewModel() }
    single { LoginViewModel() }
//    single { UnitPriceViewModel(get()) }
}