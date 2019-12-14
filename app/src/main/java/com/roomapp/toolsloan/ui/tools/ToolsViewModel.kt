package com.roomapp.toolsloan.ui.tools

import androidx.lifecycle.ViewModel
import com.roomapp.toolsloan.entity.Loan
import com.roomapp.toolsloan.entity.Tool
import com.roomapp.toolsloan.repository.LoanRepository
import com.roomapp.toolsloan.repository.ToolsRepository
import com.roomapp.toolsloan.utality.lazyDeferred
import timber.log.Timber

class ToolsViewModel(
    private val toolsRepository: ToolsRepository,
    private val loanRepository: LoanRepository
) : ViewModel() {


    val toolList by lazyDeferred{
        Timber.d("getTool List : ")
        toolsRepository.getAllTools()
    }

    fun insertLoan(loan: Loan){
        loanRepository.insertLoan(loan)
    }

    fun updateToolsCount(tool: Tool) {
        toolsRepository.updateAvailableToolCount(tool)
    }
}
