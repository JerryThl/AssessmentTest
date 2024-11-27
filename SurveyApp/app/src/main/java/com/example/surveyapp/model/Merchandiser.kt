package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "merchandiser",
    foreignKeys = [
        ForeignKey(
            entity = Outlet::class,
            parentColumns = arrayOf("outlet_id"),
            childColumns = arrayOf("location_fk_id")
        )
    ]
)
data class Merchandiser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "merchandiser_id")
    var merchandiserId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "age")
    var age: Int,
    @ColumnInfo(name = "location_fk_id")
    var locationFKId: Int
)
