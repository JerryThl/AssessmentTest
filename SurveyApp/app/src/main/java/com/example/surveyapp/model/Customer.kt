package com.example.surveyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "customer",
    foreignKeys = [
        ForeignKey(
            entity = Survey::class,
            parentColumns = arrayOf("survey_id"),
            childColumns = arrayOf("survey_fk_id")
        ),
        ForeignKey(
            entity = Outlet::class,
            parentColumns = arrayOf("outlet_id"),
            childColumns = arrayOf("outlet_fk_id")
        )]
)
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customer_id")
    var customerId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "age")
    var age: Int,
    @ColumnInfo(name = "outlet_fk_id")
    var outletFKId: Int,
    @ColumnInfo(name = "date")
    val date: Int,
    @ColumnInfo(name = "survey_fk_id")
    var surveyFkId: Int
)
