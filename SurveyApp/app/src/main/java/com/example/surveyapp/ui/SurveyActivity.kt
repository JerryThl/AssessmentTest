package com.example.surveyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surveyapp.adapters.SurveyAdapter
import com.example.surveyapp.databinding.ActivitySurveyBinding
import com.example.surveyapp.db.SurveyDatabase
import com.example.surveyapp.model.AdapterData
import com.example.surveyapp.model.Answer
import com.example.surveyapp.model.Merchandiser
import com.example.surveyapp.model.Outlet
import com.example.surveyapp.model.Questionnaire
import com.example.surveyapp.model.Survey
import com.example.surveyapp.repositary.Repository
import com.example.surveyapp.viewmodel.SurveyViewModel
import com.example.surveyapp.viewmodel.SurveyViewModelProviderFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch



class SurveyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySurveyBinding
    private lateinit var surveyViewModel: SurveyViewModel
    private lateinit var surveyAdapter: SurveyAdapter
    private var adapterData = AdapterData()

    private lateinit var completeState: MutableList<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        completeState = mutableListOf(false, false, false,false,false)

        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        populateSurvey()

    }


    private fun populateSurvey() {
        surveyViewModel.getAllSurvey().observe(this, Observer { survey ->
            if (survey.isEmpty()) {
                surveyViewModel.insertSurvey(Survey(1, "Survey Example"))

                var sData: Survey
                lifecycleScope.launch {
                    sData = surveyViewModel.getSurvey(1)
                    combineData((listOf(sData)))

                }
            } else {
                combineData(survey, null, null)
            }
            populateQuestion()
        })
    }

    private fun populateQuestion() {
        surveyViewModel.getAllQuestionnaire().observe(this, Observer { questionnaire ->
            if (questionnaire.isEmpty()) {
                surveyViewModel.insertQuestionnaire(
                    Questionnaire(
                        111,
                        1,
                        "Which brand would you prefer?"
                    )
                )
                surveyViewModel.insertQuestionnaire(Questionnaire(222, 1, "Reason for choosing this brand?"))
                surveyViewModel.insertQuestionnaire(
                    Questionnaire(
                        333,
                        1,
                        "I'm very satisfied with the shopping experience here."
                    )
                )
                surveyViewModel.insertQuestionnaire(
                    Questionnaire(
                        444,
                        1,
                        "Your Name:"
                    )
                )
                surveyViewModel.insertQuestionnaire(
                    Questionnaire(
                        555,
                        1,
                        "Shopping Venue"
                    )
                )

                surveyViewModel.insertQuestionnaire(
                    Questionnaire(
                        666,
                        1,
                        "Served By:"
                    )
                )
                surveyViewModel.getAllQuestionnaireFromSurvey(1).observe(this, Observer {
                    combineData(null, it, null)
                })

            } else {

                surveyViewModel.getAllSurvey().observe(this, Observer { surveyList ->
                    surveyList.last().surveyId?.let {
                        surveyViewModel.getAllQuestionnaireFromSurvey(it)
                            .observe(this, Observer { quesList ->
                                combineData(null, quesList, null)
                            })
                    }
                })

            }

            populateAnswer()
        })
    }

    private fun populateAnswer() {
        surveyViewModel.getAllAnswer().observe(this, Observer { answer ->
            if (answer.isEmpty()) {
                surveyViewModel.insertAnswer(Answer(11, "Brand A|Brand B", "Radio", 111))
                surveyViewModel.insertAnswer(Answer(22, "", "Edit", 222))
                surveyViewModel.insertAnswer(Answer(33, "Disagree|Neutral|Agree", "Radio", 333))
                surveyViewModel.insertAnswer(Answer(44, "", "Edit", 444))
                surveyViewModel.insertAnswer(Answer(55, "", "OutletRadio", 555))
                surveyViewModel.insertAnswer(Answer(66, "", "MerchandiserRadio", 666))

                surveyViewModel.getAllAnswerFromQuestionnaire(listOf(111, 222, 333))
                    .observe(this, Observer {
                        combineData(null, null, it)
                    })

            } else {
                surveyViewModel.getAllSurvey().observe(this, Observer { surveyList ->
                    surveyList.last().surveyId?.let {
                        surveyViewModel.getAllQuestionnaireFromSurvey(it)
                            .observe(this, Observer { quesList ->

                                var quesIdList: MutableList<Int> = mutableListOf()
                                var count = 0
                                for (id in quesList) {
                                    id.questionnaireId?.let { qId ->
                                        quesIdList.add(count, qId)
                                        count++
                                    }
                                }
                                surveyViewModel.getAllAnswerFromQuestionnaire(quesIdList)
                                    .observe(this, Observer { answerList ->
                                        combineData(null, null, answerList)
                                    })
                            })
                    }
                })
            }
            populateOutlet()

        })
    }

    private fun populateOutlet() {
        surveyViewModel.getAllOutlet().observe(this, Observer { o ->
            if (o.isEmpty()) {
                surveyViewModel.insertOutlet(Outlet(1000,"Pearl Point", "Old Klang Road"))
                surveyViewModel.insertOutlet(Outlet(1001,"Scot Garden", "Old Klang Road"))

                combineData(null, null, null,
                    listOf(Outlet(1000,"Pearl Point", "Old Klang Road"),
                        Outlet(1001,"Scot Garden", "Old Klang Road")))

            } else {
                surveyViewModel.getAllOutlet().observe(this, Observer {
                    combineData(null, null, null, it)
                })
            }
            populateMerchandiser()
        })
    }

    private fun populateMerchandiser() {
        surveyViewModel.getAllMerchandiser().observe(this, Observer { o ->
            if (o.isEmpty()) {
                surveyViewModel.insertMerchandiser(Merchandiser(1,"Muthu", 35,1000))
                    combineData(null, null, null, null, listOf(Merchandiser(1,"Muthu", 35,1000)))
            } else {
                surveyViewModel.getAllMerchandiser().observe(this, Observer {
                    combineData(null, null, null, null, it)
                })
            }
        })
    }

    private fun initViewModel() {
        val repository = Repository(SurveyDatabase(this))
        val viewModelProviderFactory = SurveyViewModelProviderFactory(application, repository)
        surveyViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SurveyViewModel::class.java)
    }

    private fun combineData(
        surveyData: List<Survey>? = null,
        questData: List<Questionnaire>? = null,
        answerData: List<Answer>? = null,
        outletData: List<Outlet>? = null,
        merchandiserData: List<Merchandiser>? = null
    ) {

        if (surveyData?.isNotEmpty() == true) {
            adapterData.surveyList = surveyData
            completeState[0] = true
        }

        if (questData?.isNotEmpty() == true) {
            adapterData.questionnaireList = questData
            completeState[1] = true
        }

        if (answerData?.isNotEmpty() == true) {
            adapterData.answerList = answerData
            completeState[2] = true
        }

        if (outletData?.isNotEmpty() == true) {
            adapterData.outletList = outletData
            completeState[3] = true
        }

        if (merchandiserData?.isNotEmpty() == true) {
            adapterData.merchandiserList = merchandiserData
            completeState[4] = true
        }

        if (completeState.all { it == true }) {
            initRecyclerView(adapterData)
        }

    }

    private fun initRecyclerView(data: AdapterData) {

        surveyAdapter = SurveyAdapter(data)
        binding.recyclerSurvey.apply {
            adapter = surveyAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }
    }
}