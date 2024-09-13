package data.repository

import data.db.entity.LoginEntity
import data.model.EmployeeModel
import util.Response
import util.Result

interface LoginRepository {

    suspend fun getInfo(employee: String, password: String): Result<Response<EmployeeModel>>

    suspend fun putLoginInfo(entity: LoginEntity)
}