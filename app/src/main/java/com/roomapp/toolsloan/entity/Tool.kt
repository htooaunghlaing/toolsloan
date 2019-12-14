package com.roomapp.toolsloan.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tools")
data class Tool(val toolName: String, val totalCount: Int,val drawable: Int) {

    @PrimaryKey(autoGenerate = true)
    var toolId: Int =0


    @ColumnInfo (name ="borrowedCount")
    var count:  Int =0


}