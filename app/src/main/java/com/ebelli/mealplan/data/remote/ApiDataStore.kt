package com.ebelli.mealplan.data.remote

class ApiDataStore(private val apiService: ApiService) {

    suspend fun getMeals() = apiService.getMeals()
}