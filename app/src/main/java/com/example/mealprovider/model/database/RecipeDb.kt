package com.example.mealprovider.model.database

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import kotlin.random.Random

class RecipeDb (private val dbHelper: RecipeDbHelper = RecipeDbHelper.instance){
    fun addMeal(recipeName : String) = dbHelper.use {
        insert(
            MealTable.NAME,
            MealTable.DESCRIPTION to recipeName)
    }

    fun getAllMeal() = dbHelper.use {
        select(
            MealTable.NAME
        ).parseList(classParser<Meal>())
    }

    fun getRandomMeal(): String {
        val meals = getAllMeal()
        return meals[Random.nextInt(0, meals.size)].NAME
    }
}