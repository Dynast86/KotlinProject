package data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val employee: String,
    val name: String,
    val dept: String,
    val authCode: String
)