package com.roomapp.toolsloan.repository

import androidx.lifecycle.LiveData
import com.roomapp.toolsloan.entity.Friend

interface FriendRepository {

    suspend fun getAllFriends() : LiveData<List<Friend>>
}