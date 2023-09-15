package com.example.mealprovider.model.recyclerview

import com.example.mealprovider.App
import com.example.mealprovider.model.database.Meal

class MealInDaPlaceRecyclerViewProvider {
    private val mealList: List<Meal> = App.db.getAllMeal()

    fun getMealsInDaPlaceAlphabeticalOrder(): Array<MealInDaPlace> {
        var mealInDaPlaceArray = emptyArray<MealInDaPlace>()
        mealList.sortedBy { it.NAME }.forEach { m -> mealInDaPlaceArray += MealInDaPlace(m.NAME, m.ID) }

        return mealInDaPlaceArray
    }
}