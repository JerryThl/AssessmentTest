package com.example.surveyapp.repositary

import com.example.surveyapp.db.SurveyDatabase
import com.example.surveyapp.model.Answer
import com.example.surveyapp.model.Credential
import com.example.surveyapp.model.Customer
import com.example.surveyapp.model.Merchandiser
import com.example.surveyapp.model.Outlet
import com.example.surveyapp.model.Questionnaire
import com.example.surveyapp.model.Survey

class Repository(val db: SurveyDatabase) {

    // Survey
   suspend fun getSurvey(id: Int) =
        db.surveyDAO().getSurvey(id)

    fun getAllSurvey() = db.surveyDAO().getAllSurvey()

    suspend fun upsertSurvey(survey: Survey):Long = db.surveyDAO().upsertSurvey(survey)

    fun getAllQuestionnaireFromSurvey(surveyId: Int) = db.surveyDAO().getAllQuestionnaireFromSurvey(surveyId)


    // Questionnaire
    suspend fun getQuestionnaire(id: Int) =
        db.surveyDAO().getQuestionnaire(id)

    fun getAllQuestionnaire() = db.surveyDAO().getAllQuestionnaire()

    suspend fun upsertQuestionnaire(questionnaire: Questionnaire) =
        db.surveyDAO().upsertQuestionnaire(questionnaire)

    // Answer
    suspend fun getAnswer(id: Int) =
        db.surveyDAO().getAnswer(id)

    fun getAllAnswer() = db.surveyDAO().getAllAnswer()

    fun getAllAnswerFromQuestionnaire(questionnaireListId: List<Int>) = db.surveyDAO().getAllAnswerFromQuestionnaire(questionnaireListId)


    suspend fun upsertAnswer(answer: Answer) = db.surveyDAO().upsertAnswer(answer)

    //Customer
    suspend fun getCustomer(id: Int) =
        db.surveyDAO().getCustomer(id)

    suspend fun getAllCustomer() = db.surveyDAO().getAllCustomer()

    suspend fun upsertCustomer(customer: Customer) = db.surveyDAO().upsertCustomer(customer)

    //Outlet
    suspend fun getOutlet(id: Int) =
        db.surveyDAO().getOutlet(id)

    fun getAllOutlet() = db.surveyDAO().getAllOutlet()

    suspend fun upsertOutlet(outlet: Outlet) = db.surveyDAO().upsertOutlet(outlet)

    //Merchandiser
    suspend fun getMerchandiser(id: Int) =
        db.surveyDAO().getMerchandiser(id)

    fun getAllMerchandiser() = db.surveyDAO().getAllMerchandiser()

    suspend fun upsertMerchandiser(merchandiser: Merchandiser) =
        db.surveyDAO().upsertMerchandiser(merchandiser)

    // Credential
    suspend fun getCredential(merchandiser_id: Int) =
        db.surveyDAO().getCredential(merchandiser_id)

    suspend fun getAllCredential() = db.surveyDAO().getAllCredential()

    suspend fun upsertCredential(credential: Credential) =
        db.surveyDAO().upsertCredential(credential)

}