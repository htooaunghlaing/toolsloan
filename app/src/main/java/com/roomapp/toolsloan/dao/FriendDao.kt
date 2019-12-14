package com.roomapp.toolsloan.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roomapp.toolsloan.entity.Friend

@Dao
interface FriendDao {

    @Insert
    fun insertFriend(friend: Friend)

    @Insert
    fun insertFriend(data: List<Friend>)


    @Query("select * from Friends")
    fun getAllFriends(): LiveData<List<Friend>>
}