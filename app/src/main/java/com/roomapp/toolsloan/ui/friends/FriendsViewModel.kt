package com.roomapp.toolsloan.ui.friends

import androidx.lifecycle.ViewModel
import com.roomapp.toolsloan.repository.FriendRepository
import com.roomapp.toolsloan.utality.lazyDeferred
import timber.log.Timber

class FriendsViewModel(
    private val friendRepository: FriendRepository
) : ViewModel() {


    val friendList by lazyDeferred{
        Timber.d("getTool List : ")
        friendRepository.getAllFriends()
    }
}
