package org.example.project.domain

import io.ktor.client.HttpClient
import org.example.project.data.Service.getLoginInfo
import org.example.project.data.model.EmployeeModel
import org.example.project.data.repository.LoginRepository
import org.example.project.util.Response
import org.example.project.util.Result
import org.example.project.util.map

class LoginRepositoryImpl(
    private val apiService: HttpClient
) : LoginRepository {

    override suspend fun getInfo(
        employee: String,
        password: String
    ): Result<Response<EmployeeModel>> {
        return apiService.getLoginInfo(id = employee, password = password)
    }
}