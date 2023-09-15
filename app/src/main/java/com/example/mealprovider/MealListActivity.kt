package com.example.mealprovider

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealprovider.model.recyclerview.MealInDaPlace
import com.example.mealprovider.model.recyclerview.MealInDaPlaceAdapter
import com.example.mealprovider.model.recyclerview.MealInDaPlaceRecyclerViewProvider
import kotlinx.android.synthetic.main.activity_list_meal.*

class MealListActivity : AppCompatActivity() {
    private lateinit var mealInDaPlaceArray: Array<MealInDaPlace>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(R.layout.activity_list_meal)
        // set recyclerView
        andMealRecyclerView.layoutManager = LinearLayoutManager(this)
        andMealRecyclerView.adapter = MealInDaPlaceAdapter(mealInDaPlaceArray, this)
        // add back button listener
        cancelListMeal.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun init() {
        mealInDaPlaceArray = MealInDaPlaceRecyclerViewProvider().getMealsInDaPlaceAlphabeticalOrder()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                enterTransition = Explode()
                exitTransition = Explode()
            }
        }
    }
}