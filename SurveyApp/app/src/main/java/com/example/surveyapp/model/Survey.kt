package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "survey"
)

data class Survey(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "survey_id")
    var surveyId: Int? = null,
    @ColumnInfo(name = "title")
    var title: String
)

