package com.roomapp.toolsloan.ui.tools

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roomapp.toolsloan.repository.LoanRepository
import com.roomapp.toolsloan.repository.ToolsRepository

class ToolsViewModelFactory(
    private val toolsRepository: ToolsRepository,
    private val loanRepository: LoanRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ToolsViewModel(toolsRepository,loanRepository) as T
    }
}