package com.example.surveyapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.surveyapp.repositary.Repository

class SurveyViewModelProviderFactory(
    val app: Application,
    val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SurveyViewModel(app, repository) as T
    }
}