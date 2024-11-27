package com.example.surveyapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.surveyapp.R
import com.example.surveyapp.model.AdapterData
import com.example.surveyapp.model.Questionnaire
import com.example.surveyapp.model.Survey
import com.example.surveyapp.viewmodel.SurveyViewModel
import kotlinx.coroutines.launch

class SurveyAdapter(private val dataList: AdapterData) :
    RecyclerView.Adapter<SurveyAdapter.SurveyViewHolder>() {

    //inner class has access to outer class
    inner class SurveyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    lateinit var questionNAnswer_ll: LinearLayout

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
        return SurveyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_survey, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.questionnaireList.size
    }


    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        questionNAnswer_ll = holder.itemView.findViewById(R.id.questionNAnswer_ll)

        val ques = dataList.questionnaireList[position]
        val ans = dataList.answerList
        val outlet = dataList.outletList
        val merchan = dataList.merchandiserList

        val rg = RadioGroup(holder.itemView.context)
        rg.orientation = RadioGroup.HORIZONTAL

        // Add textview based on questionnaire size
        val tv = TextView(holder.itemView.context)
        tv.text = ques.questionText
        questionNAnswer_ll.addView(tv)

        for (a in ans) {
            if (a.questionnaireFkId == ques.questionnaireId) {
                when (a.answerType) {
                    "Radio" -> {
                        val rb_value = a.answerText.split("|")

                        for ((index, value) in rb_value.withIndex()) {
                            val rb = RadioButton(holder.itemView.context)
                            rb.text = value
                            rb.tag = "rb_$position _$index"
                            rg.addView(rb)
                        }

                        questionNAnswer_ll.addView(rg)

                    }

                    "Edit" -> {
                        var et = EditText(holder.itemView.context)
                        et.tag = "et_$position"
                        questionNAnswer_ll.addView(et)
                    }

                    "OutletRadio"->{
                        for((index, value) in outlet.withIndex()){
                            val rb = RadioButton(holder.itemView.context)
                            rb.text = value.name
                            rb.tag = "rb_$position _$index"
                            rg.addView(rb)
                        }
                        questionNAnswer_ll.addView(rg)

                    }

                    "MerchandiserRadio"->{
                        for((index, value) in merchan.withIndex()){
                            val rb = RadioButton(holder.itemView.context)
                            rb.text = value.name
                            rb.tag = "rb_$position _$index"
                            rg.addView(rb)
                        }
                        questionNAnswer_ll.addView(rg)
                    }
                }
            }
        }
    }

}