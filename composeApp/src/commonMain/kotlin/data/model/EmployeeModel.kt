package data.model

import kotlinx.serialization.Serializable
import database.entity.LoginEntity

@Serializable
data class EmployeeModel(
    val employee: String,
    val name: String,
    val dept: String,
    val authCode: String
)

fun EmployeeModel.toEntity() = LoginEntity(
    employee = employee,
    name = name,
    dept = dept,
    authCode = authCode
)