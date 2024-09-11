package org.example.project.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.example.project.database.entity.LoginEntity

@Dao
interface LoginDao {
    @Insert
    suspend fun insert(item: LoginEntity)

    @Query("SELECT count(*) FROM LoginEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM LoginEntity")
    fun getAllAsFlow(): Flow<List<LoginEntity>>
}
