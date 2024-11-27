package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "credential",
    foreignKeys = [
        ForeignKey(
            entity = Merchandiser::class,
            parentColumns = arrayOf("merchandiser_id"),
            childColumns = arrayOf("merchandiser_fk_id")
        )
    ]
)

data class Credential(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "credential_id")
    var credentilId: Int? = null,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "merchandiser_fk_id")
    var merchandiserFKId: Int
)
