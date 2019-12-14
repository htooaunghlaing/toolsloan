package com.roomapp.toolsloan.repository

import androidx.lifecycle.LiveData
import com.roomapp.toolsloan.entity.LoanWithFriendAndTool

interface FriendDetailsRepository {

    suspend fun getAllToolsByFriendName(friendName: String) : LiveData<List<LoanWithFriendAndTool>>
}