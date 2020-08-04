package com.ebelli.mealplan.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebelli.mealplan.R
import com.ebelli.mealplan.data.models.Meal
import com.ebelli.mealplan.data.remote.ApiDataStore
import com.ebelli.mealplan.data.remote.RetrofitBuilder
import com.ebelli.mealplan.ui.common.ViewModelFactory
import com.ebelli.mealplan.ui.main.adapter.MealsAdapter
import com.ebelli.mealplan.ui.utils.Status
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MealsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiDataStore(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        recyclerView_meals.layoutManager = LinearLayoutManager(context)
        adapter = MealsAdapter(arrayListOf())
        recyclerView_meals.addItemDecoration(
            DividerItemDecoration(
                recyclerView_meals.context,
                (recyclerView_meals.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView_meals.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getMeals().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView_meals.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { meals -> setMeals(meals) }
                    }
                    Status.ERROR -> {
                        it.message?.let { message ->
                            val snackbar = Snackbar.make(recyclerView_meals,
                                message, Snackbar.LENGTH_LONG)
                            snackbar.show()
                            Log.e("Meals", message )
                        }
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView_meals.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setMeals(users: List<Meal>) {
        adapter.apply {
            addMeals(users)
            notifyDataSetChanged()
        }
    }
}