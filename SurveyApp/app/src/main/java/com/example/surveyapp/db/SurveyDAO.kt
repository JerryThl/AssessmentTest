package com.example.surveyapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.surveyapp.model.Answer
import com.example.surveyapp.model.Credential
import com.example.surveyapp.model.Customer
import com.example.surveyapp.model.Merchandiser
import com.example.surveyapp.model.Outlet
import com.example.surveyapp.model.Questionnaire
import com.example.surveyapp.model.Survey

@Dao
interface SurveyDAO {

    //Survey
    @Query("SELECT * FROM survey")
    fun getAllSurvey(): LiveData<List<Survey>>

    @Query("SELECT * FROM survey WHERE survey_id = :surveyId")
    suspend fun getSurvey(surveyId: Int): Survey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSurvey(survey: Survey): Long

    @Delete
    suspend fun deleteSurvey(survey: Survey)

    //Questionnaire
    @Query("SELECT * FROM questionnaire")
    fun getAllQuestionnaire(): LiveData<List<Questionnaire>>

    @Query("SELECT * FROM questionnaire WHERE questionnaire_id = :questionnaireId")
    suspend fun getQuestionnaire(questionnaireId: Int): Questionnaire

    @Query("SELECT * FROM (SELECT * FROM questionnaire " +
            "INNER JOIN survey ON questionnaire.survey_fk_id = survey.survey_id WHERE survey.survey_id = :surveyId)")
    fun getAllQuestionnaireFromSurvey(surveyId: Int): LiveData<List<Questionnaire>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertQuestionnaire(questionnaire: Questionnaire): Long

    @Delete
    suspend fun deleteQuestionnaire(questionnaire: Questionnaire)

    //Answer
    @Query("SELECT * FROM answer")
    fun getAllAnswer(): LiveData<List<Answer>>

    @Query("SELECT * FROM answer WHERE answer_id = :answerId")
    suspend fun getAnswer(answerId: Int): Answer

    @Query("SELECT * FROM (SELECT * FROM answer " +
            "INNER JOIN questionnaire ON answer.questionnaire_fk_id = questionnaire.questionnaire_id WHERE questionnaire.questionnaire_id IN (:questionnairListId))")
    fun getAllAnswerFromQuestionnaire(questionnairListId: List<Int>): LiveData<List<Answer>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAnswer(answer: Answer): Long

    @Delete
    suspend fun deleteAnswer(answer: Answer)

    //Customer
    @Query("SELECT * FROM customer")
    suspend fun getAllCustomer(): List<Customer>

    @Query("SELECT * FROM customer WHERE customer_id = :customerId")
    suspend fun getCustomer(customerId: Int): Customer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCustomer(answer: Customer): Long

    @Delete
    suspend fun deleteCustomer(answer: Customer)

    //Merchandiser
    @Query("SELECT * FROM merchandiser")
    fun getAllMerchandiser(): LiveData<List<Merchandiser>>

    @Query("SELECT * FROM merchandiser WHERE merchandiser_id = :merchandiserId")
    suspend fun getMerchandiser(merchandiserId: Int): Merchandiser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMerchandiser(answer: Merchandiser): Long

    @Delete
    suspend fun deleteMerchandiser(answer: Merchandiser)

    //Outlet
    @Query("SELECT * FROM outlet")
    fun getAllOutlet(): LiveData<List<Outlet>>

    @Query("SELECT * FROM outlet WHERE outlet_id = :outletId")
    suspend fun getOutlet(outletId: Int): Outlet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertOutlet(outlet: Outlet): Long

    @Delete
    suspend fun deleteOutlet(outlet: Outlet)

    //Credential
    @Query("SELECT * FROM credential")
    suspend fun getAllCredential(): List<Credential>

    @Query("SELECT * FROM credential WHERE merchandiser_fk_id = :merchandiserId")
    suspend fun getCredential(merchandiserId: Int): Credential

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCredential(credential: Credential): Long

    @Delete
    suspend fun deleteCredential(credential: Credential)
}