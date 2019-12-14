package com.roomapp.toolsloan.repository

import com.roomapp.toolsloan.entity.Loan

interface LoanRepository {

    fun insertLoan(loan: Loan)


    fun removeTool(toolID: String)

}