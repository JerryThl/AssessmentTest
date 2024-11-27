package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "outlet"
)
data class Outlet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "outlet_id")
    var outletId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "location")
    var location:String
)
