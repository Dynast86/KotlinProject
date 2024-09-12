package domain

import io.ktor.client.HttpClient
import data.Service.getLoginInfo
import data.model.EmployeeModel
import data.repository.LoginRepository
import database.AppDatabase
import util.Response
import util.Result

class LoginRepositoryImpl(
    private val apiService: HttpClient,
    private val database: AppDatabase,
) : LoginRepository {

    override suspend fun getInfo(
        employee: String,
        password: String
    ): Result<Response<EmployeeModel>> {
        return apiService.getLoginInfo(id = employee, password = password)
    }

//    override suspend fun putLoginInfo(entity: LoginEntity) {
//        data.database.getLoginDao().insert(entity)
//    }
}