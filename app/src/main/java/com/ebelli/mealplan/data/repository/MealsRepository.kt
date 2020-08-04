package com.ebelli.mealplan.data.repository

import com.ebelli.mealplan.data.remote.ApiDataStore

class MealsRepository(private val apiDataStore: ApiDataStore) {

    suspend fun getMeals() = apiDataStore.getMeals()
}