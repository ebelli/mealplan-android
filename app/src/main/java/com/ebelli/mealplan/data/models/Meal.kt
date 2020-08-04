package com.ebelli.mealplan.data.models

data class Meal(
    val id: Long,
    val type: MealType,
    val description: String
)