package com.roomapp.toolsloan.repository

import androidx.lifecycle.LiveData
import com.roomapp.toolsloan.entity.Tool

interface ToolsRepository {

    suspend fun getAllTools() : LiveData<List<Tool>>
    fun insertTool(tool: Tool)
    fun updateAvailableToolCount(tool: Tool)
    fun decreaseAvaliableCount(toolID: Int)
}