package com.roomapp.toolsloan.repository

import androidx.lifecycle.LiveData
import com.roomapp.toolsloan.dao.FriendDao
import com.roomapp.toolsloan.entity.Friend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FriendRepositoryImpl(
    private val friendDao: FriendDao
) : FriendRepository {
    override suspend fun getAllFriends(): LiveData<List<Friend>> {
        return withContext(Dispatchers.IO){
            return@withContext friendDao.getAllFriends()
        }
    }
}