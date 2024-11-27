package com.example.surveyapp.model

import androidx.lifecycle.LiveData

data class AdapterData(
    var surveyList: List<Survey> = emptyList(),
    var questionnaireList: List<Questionnaire> = emptyList(),
    var answerList: List<Answer> = emptyList(),
    var outletList: List<Outlet> = emptyList(),
    var merchandiserList: List<Merchandiser> = emptyList()
)