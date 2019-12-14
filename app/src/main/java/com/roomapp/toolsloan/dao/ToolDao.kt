package com.roomapp.toolsloan.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.roomapp.toolsloan.entity.Tool

@Dao
interface ToolDao
{
    @Insert
    fun insertTools(tool: Tool)

    @Insert
    fun insertTools(data: List<Tool>)


    @Query("select * from tools")
    fun getAllTools(): LiveData<List<Tool>>

    @Query("SELECT * FROM tools WHERE toolId = :toolID")
    fun getTool(toolID: Int): LiveData<Tool>

    @Update
    fun increaseAvailableToolCount(tool: Tool)

    @Query("UPDATE tools SET borrowedCount = borrowedCount - 1 where toolId = :tool_id")
    fun decreaseBorrowedCount(tool_id: Int)


}