package com.roomapp.toolsloan.ui.friend_details

import androidx.lifecycle.ViewModel
import com.roomapp.toolsloan.repository.FriendDetailsRepository
import com.roomapp.toolsloan.repository.LoanRepository
import com.roomapp.toolsloan.repository.ToolsRepository
import com.roomapp.toolsloan.utality.lazyDeferred


class FriendDetailsViewModel(
    private val friendDetailsRepository: FriendDetailsRepository,
    private val loanRepository: LoanRepository,
    private val toolsRepository: ToolsRepository
) : ViewModel() {

    var name: String = ""


    val toolListbyFriendName by lazyDeferred{
            friendDetailsRepository.getAllToolsByFriendName(name)
    }

    fun removeToolFromLoan(loanID: String) {
        loanRepository.removeTool(loanID)
    }

    fun updateAvailableCount(toolID: Int) {
        toolsRepository.decreaseAvaliableCount(toolID)
    }
}