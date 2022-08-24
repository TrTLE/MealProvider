package com.example.mealprovider.model.database

object RecipeTable {
    val NAME = "RECIPE"
    val ID = "_id"
    val INGREDIENT_ID = "INGREDIENT_ID"
}

object IngredientTable {
    val NAME = "INGREDIENT"
    val ID = "_id"
    val DESCRIPTION = "NAME"
}

object MealTable {
    val NAME = "MEAL"
    val RECIPE_ID = "MEAL_ID"
    val DESCRIPTION = "NAME"
    val URL = "URL"
    val RESUME = "RESUME"
}