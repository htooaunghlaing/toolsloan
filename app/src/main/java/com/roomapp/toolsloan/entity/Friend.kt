package com.roomapp.toolsloan.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Friends")
data class Friend(val friendName: String) {

    @PrimaryKey(autoGenerate = true)
    var friendID: Int =0

}