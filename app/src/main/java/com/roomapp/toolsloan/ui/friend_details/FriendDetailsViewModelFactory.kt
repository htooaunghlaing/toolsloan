package com.roomapp.toolsloan.ui.friend_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roomapp.toolsloan.repository.FriendDetailsRepository
import com.roomapp.toolsloan.repository.LoanRepository
import com.roomapp.toolsloan.repository.ToolsRepository


class FriendDetailsViewModelFactory(
    private val friendDetailsRepository: FriendDetailsRepository,
    private val loanRepository: LoanRepository,
    private val toolsRepository: ToolsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FriendDetailsViewModel(friendDetailsRepository,loanRepository,toolsRepository) as T
    }
}