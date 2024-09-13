package domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val employee: String?,
    val name: String?,
    val dept: String?
)
