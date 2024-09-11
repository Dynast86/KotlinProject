package org.example.project.data.repository

import org.example.project.data.model.EmployeeModel
import org.example.project.util.Response
import org.example.project.util.Result

interface LoginRepository {

    suspend fun getInfo(employee: String, password: String): Result<Response<EmployeeModel>>
}