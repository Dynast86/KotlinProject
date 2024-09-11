package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeModel(
    val employee: String,
    val name: String,
    val dept: String,
    val authCode: String
)