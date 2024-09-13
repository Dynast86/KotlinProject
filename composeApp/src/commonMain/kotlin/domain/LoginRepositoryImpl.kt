package domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.HttpClient
import data.Service.getLoginInfo
import data.model.EmployeeModel
import data.repository.LoginRepository
import data.db.AppDatabase
import data.db.entity.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import util.Response
import util.Result

class LoginRepositoryImpl(
    private val apiService: HttpClient,
    private val database: AppDatabase,
    private val dataStore: DataStore<Preferences>
) : LoginRepository {

    companion object {
        const val EMPLOYEE = "employee"
        const val NAME = "name"
        const val DEPT = "dept"
    }

    private val employeeKey = stringPreferencesKey(EMPLOYEE)
    private val nameKey = stringPreferencesKey(NAME)
    private val deptKey = stringPreferencesKey(DEPT)

    override suspend fun getInfo(
        employee: String,
        password: String
    ): Result<Response<EmployeeModel>> {
        return apiService.getLoginInfo(id = employee, password = password)
    }

    override suspend fun putLoginInfo(entity: LoginEntity) {
//        database.getLoginDao().insert(entity)
        dataStore.edit {
            it[employeeKey] = entity.employee
            it[nameKey] = entity.name
            it[deptKey] = entity.dept
        }
    }

    val info: Flow<LoginData> = dataStore.data.map {
        LoginData(
            it[employeeKey] ?: "",
            it[nameKey] ?: "",
            it[deptKey] ?: "",
        )
    }
}