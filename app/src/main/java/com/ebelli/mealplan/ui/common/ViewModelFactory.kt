package com.ebelli.mealplan.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ebelli.mealplan.data.remote.ApiDataStore
import com.ebelli.mealplan.data.repository.MealsRepository
import com.ebelli.mealplan.ui.main.MainViewModel

class ViewModelFactory(private val apiDataStore: ApiDataStore): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MealsRepository(apiDataStore)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}