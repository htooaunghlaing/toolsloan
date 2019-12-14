package com.roomapp.toolsloan.repository

import androidx.lifecycle.LiveData
import com.roomapp.toolsloan.dao.ToolDao
import com.roomapp.toolsloan.entity.Tool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ToolsRepositoryImpl(
    private val toolDao: ToolDao
) : ToolsRepository {
    override fun insertTool(tool: Tool) {
        GlobalScope.launch {
            toolDao.insertTools(tool)
        }
    }


    override fun updateAvailableToolCount(tool: Tool) {
        GlobalScope.launch {
            tool.count++
            Timber.e("Tool Update count %d Total Count %d", tool.count, tool.totalCount)
            toolDao.increaseAvailableToolCount(tool)
        }
    }

    override fun decreaseAvaliableCount(toolID: Int) {
        GlobalScope.launch {
            toolDao.decreaseBorrowedCount(toolID)
        }
    }

    override suspend fun getAllTools(): LiveData<List<Tool>> {
        return withContext(Dispatchers.IO){
            return@withContext toolDao.getAllTools()
        }
    }
}