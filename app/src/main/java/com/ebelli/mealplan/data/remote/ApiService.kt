package com.ebelli.mealplan.data.remote

import com.ebelli.mealplan.data.models.Meal
import retrofit2.http.GET

interface ApiService {

    @GET("meals")
    suspend fun getMeals(): List<Meal>

}