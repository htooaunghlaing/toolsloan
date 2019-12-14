package com.roomapp.toolsloan.repository

import com.roomapp.toolsloan.dao.LoanDao
import com.roomapp.toolsloan.entity.Loan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoanRepositoryImpl(
    private val loanDao: LoanDao
) : LoanRepository {
    override fun removeTool(loanID: String) {
        GlobalScope.launch(Dispatchers.IO){
            loanDao.removeTool(loanID)
        }
    }

    override fun insertLoan(loan: Loan) {
        GlobalScope.launch(Dispatchers.IO) {
            loanDao.insertLoan(loan)
        }
    }
}