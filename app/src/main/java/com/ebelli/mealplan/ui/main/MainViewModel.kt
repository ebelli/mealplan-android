package com.ebelli.mealplan.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ebelli.mealplan.data.repository.MealsRepository
import com.ebelli.mealplan.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mealsRepository: MealsRepository) : ViewModel() {

    fun getMeals() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mealsRepository.getMeals()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}