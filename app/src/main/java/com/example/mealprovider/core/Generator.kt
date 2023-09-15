package com.example.mealprovider.core

import com.example.mealprovider.App
import com.example.mealprovider.model.database.Meal
import kotlin.random.Random

class Generator {

    private lateinit var mealList : MutableList<Meal>

    constructor() {
        initMealList()
    }

    private fun initMealList() {
        mealList = App.db.getAllMeal() as MutableList<Meal>
    }

    fun getNextMeal(): Meal {
        if (mealList.isEmpty()) {
            initMealList()
        }
        val mealIndex = Random.nextInt(0, mealList.size)
        val meal = mealList[mealIndex]
        mealList.removeAt(mealIndex)

        return meal
    }

    fun getNextMealName(): String {
        return getNextMeal().NAME
    }

    fun getNextMealId(): Int {
        return getNextMeal().ID
    }

}