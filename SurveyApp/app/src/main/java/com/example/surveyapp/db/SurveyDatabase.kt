package com.example.surveyapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.surveyapp.model.Answer
import com.example.surveyapp.model.Credential
import com.example.surveyapp.model.Customer
import com.example.surveyapp.model.Merchandiser
import com.example.surveyapp.model.Outlet
import com.example.surveyapp.model.Questionnaire
import com.example.surveyapp.model.Survey
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [Survey::class,
        Questionnaire::class,
        Answer::class,
        Customer::class,
        Merchandiser::class,
        Outlet::class,
        Credential::class],
    version = 1
)

abstract class SurveyDatabase:RoomDatabase() {
    abstract fun surveyDAO() : SurveyDAO

    companion object{
        @Volatile
        private var instance: SurveyDatabase? = null
        private val LOCK = Any()

        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context) = instance ?: kotlinx.coroutines.internal.synchronized(
            LOCK
        ) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SurveyDatabase::class.java,
                name = "survey_db.db"
            ).build()
    }

}