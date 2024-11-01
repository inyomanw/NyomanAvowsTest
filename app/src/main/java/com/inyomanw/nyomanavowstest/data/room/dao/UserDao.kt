package com.inyomanw.nyomanavowstest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.inyomanw.nyomanavowstest.data.room.table.AddressTable
import com.inyomanw.nyomanavowstest.data.room.table.UserTable
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userTable: UserTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userTables: List<UserTable>)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<UserTable>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): Flow<UserTable?>

    @Transaction
    @Query("""
        SELECT * FROM user 
        INNER JOIN address ON user.addressId = address.idAddress WHERE username = :username
    """)
    suspend fun getUserByUsername(username: String): UserWithAddress

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(addressTable: AddressTable)
}