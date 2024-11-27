package com.example.surveyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.surveyapp.model.Answer
import com.example.surveyapp.model.Credential
import com.example.surveyapp.model.Customer
import com.example.surveyapp.model.Merchandiser
import com.example.surveyapp.model.Outlet
import com.example.surveyapp.model.Questionnaire
import com.example.surveyapp.model.Survey
import com.example.surveyapp.repositary.Repository
import kotlinx.coroutines.launch

class SurveyViewModel(app: Application, val repository: Repository) :
    AndroidViewModel(app) {

    //Survey
    fun insertSurvey(survey: Survey) = viewModelScope.launch {
        repository.upsertSurvey(survey)
    }

    suspend fun getSurvey(id: Int) = repository.getSurvey(id)

    fun getAllSurvey() = repository.getAllSurvey()

    //Questionnaire
    fun insertQuestionnaire(questionnaire: Questionnaire) = viewModelScope.launch {
        repository.upsertQuestionnaire(questionnaire)
    }

    fun getQuestionnaire(id: Int) = viewModelScope.launch {
        repository.getQuestionnaire(id)
    }

    fun getAllQuestionnaire() = repository.getAllQuestionnaire()

    fun getAllQuestionnaireFromSurvey(surveyid: Int) = repository.getAllQuestionnaireFromSurvey(surveyid)


    //Answer
    fun insertAnswer(answer: Answer) = viewModelScope.launch {
        repository.upsertAnswer(answer)
    }

    fun getAnswer(id: Int) = viewModelScope.launch {
        repository.getAnswer(id)
    }

    fun getAllAnswer() = repository.getAllAnswer()

    fun getAllAnswerFromQuestionnaire(quesidList: List<Int>) = repository.getAllAnswerFromQuestionnaire(quesidList)

    //Customer
    fun insertCustomer(customer: Customer) = viewModelScope.launch {
        repository.upsertCustomer(customer)
    }

    fun getCustomer(id: Int) = viewModelScope.launch {
        repository.getCustomer(id)
    }


    //Outlet
    fun insertOutlet(outlet: Outlet) = viewModelScope.launch {
        repository.upsertOutlet(outlet)
    }

    fun getOutlet(id: Int) = viewModelScope.launch {
        repository.getOutlet(id)
    }
    fun getAllOutlet() = repository.getAllOutlet()


    //Merchandiser
    fun insertMerchandiser(merchandiser: Merchandiser) = viewModelScope.launch {
        repository.upsertMerchandiser(merchandiser)
    }

    fun getMerchandiser(id: Int) = viewModelScope.launch {
        repository.getMerchandiser(id)
    }

    fun getAllMerchandiser() = repository.getAllMerchandiser()

    //Credential
    fun insertCredential(credential: Credential) = viewModelScope.launch {
        repository.upsertCredential(credential)
    }

    fun getCredential(id: Int) = viewModelScope.launch {
        repository.getCredential(id)
    }

}