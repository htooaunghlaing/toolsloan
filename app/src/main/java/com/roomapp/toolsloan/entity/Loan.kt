package com.roomapp.toolsloan.entity

import androidx.room.*

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Tool::class, parentColumns = arrayOf("toolId"), childColumns = arrayOf("tool_id")),
ForeignKey(entity = Friend::class, parentColumns = arrayOf("friendID"), childColumns = arrayOf("friend_id"))))
class Loan (
            @ColumnInfo(name = "tool_id", index = true) var toolId: String? = null,
            @ColumnInfo(name = "friend_id", index = true) var friendId: String? = null){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}