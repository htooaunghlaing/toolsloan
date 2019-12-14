package com.roomapp.toolsloan.entity

import androidx.room.ColumnInfo

data class LoanWithFriendAndTool(
    var id: String,
    @ColumnInfo(name = "toolName") var toolName: String? = null,
    @ColumnInfo(name = "count")var count: Int = 0,
    @ColumnInfo(name = "toolId")var tool_id: Int = 0,
    @ColumnInfo(name = "image")var image: Int = 0

)