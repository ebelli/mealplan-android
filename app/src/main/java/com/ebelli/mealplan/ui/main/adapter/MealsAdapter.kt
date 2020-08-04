package com.ebelli.mealplan.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.mealplan.R
import com.ebelli.mealplan.data.models.Meal
import kotlinx.android.synthetic.main.meal_item_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class MealsAdapter(private val meals: ArrayList<Meal>): RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

    class MealsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(meal: Meal) {
            itemView.apply {
                text_view_type.text = meal.type.name.toLowerCase(Locale.ROOT)
                text_view_meal.text = meal.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder =
        MealsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_item_layout, parent, false))

    override fun getItemCount(): Int = meals.size


    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    fun addMeals(users: List<Meal>) {
        this.meals.apply {
            clear()
            addAll(users)
        }

    }
}