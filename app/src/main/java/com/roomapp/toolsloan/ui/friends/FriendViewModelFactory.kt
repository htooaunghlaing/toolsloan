package com.roomapp.toolsloan.ui.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roomapp.toolsloan.repository.FriendRepository

class FriendViewModelFactory(
    private val friendRepository: FriendRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FriendsViewModel(friendRepository) as T
    }
}