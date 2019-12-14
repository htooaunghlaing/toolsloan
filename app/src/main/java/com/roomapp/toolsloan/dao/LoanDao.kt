package com.roomapp.toolsloan.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roomapp.toolsloan.entity.Loan
import com.roomapp.toolsloan.entity.LoanWithFriendAndTool

@Dao
interface LoanDao {

    @Query("SELECT * From Loan")
    fun findAllLoans(): LiveData<List<Loan>>


    @Query("SELECT Loan.id,Tools.toolId,Tools.toolName,Tools.drawable as image,count(Tools.toolName) as count " +
            "from Tools " +
            "inner join Loan on Loan.tool_id = Tools.toolId " +
            "inner join Friends on Friends.friendID = Loan.friend_id " +
            "where Friends.friendName like :userName " +
            "group by Tools.toolName")
    fun findLoansByName(userName: String): LiveData<List<LoanWithFriendAndTool>>



    @Insert
    fun insertLoan(loan: Loan)

    @Query("DELETE FROM Loan")
    fun deleteAll()

    @Query("DELETE FROM Loan WHERE id = :loanID")
    fun removeTool(loanID: String)
}