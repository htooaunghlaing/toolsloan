package com.roomapp.toolsloan.repository

import androidx.lifecycle.LiveData
import com.roomapp.toolsloan.dao.LoanDao
import com.roomapp.toolsloan.entity.LoanWithFriendAndTool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FriendDetailsRepositoryImpl(
    private val loanDao: LoanDao
) : FriendDetailsRepository {
    override suspend fun getAllToolsByFriendName(friendName: String): LiveData<List<LoanWithFriendAndTool>> {
        return withContext(Dispatchers.IO){
            return@withContext loanDao.findLoansByName(friendName)
        }
    }
}