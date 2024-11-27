package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "answer",
    foreignKeys = [
        ForeignKey(
            entity = Questionnaire::class,
            parentColumns = arrayOf("questionnaire_id"),
            childColumns = arrayOf("questionnaire_fk_id")
        )]
)
class Answer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    var answerId: Int? = null,
    @ColumnInfo(name = "answer_text")
    var answerText: String,
    @ColumnInfo(name = "answer_type")
    var answerType: String,
    @ColumnInfo(name = "questionnaire_fk_id")
    var questionnaireFkId: Int,
    @ColumnInfo(name = "selected_answer")
    var selectedAnswer: String? = ""
)