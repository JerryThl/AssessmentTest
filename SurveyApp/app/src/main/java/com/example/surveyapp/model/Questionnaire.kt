package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "questionnaire",
    foreignKeys = [
        ForeignKey(
            entity = Survey::class,
            parentColumns = arrayOf("survey_id"),
            childColumns = arrayOf("survey_fk_id")
        )]
)
data class Questionnaire(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionnaire_id")
    var questionnaireId: Int? = null,
    @ColumnInfo(name = "survey_fk_id")
    var surveyFkId: Int,
    @ColumnInfo(name = "question_text")
    var questionText: String

)
