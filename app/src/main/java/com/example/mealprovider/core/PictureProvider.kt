package com.example.mealprovider.core

import kotlinx.android.synthetic.main.activity_details_meal.*

class PictureProvider {

    companion object {

        val mealPicture: Map<Int, String> = mapOf(
            1 to "recipe_picture",
            2 to "recipe_picture",
            3 to "recipe_picture",
            4 to "recipe_picture",
            5 to "recipe_picture",
            6 to "recipe_picture",
            7 to "recipe_picture",
            8 to "recipe_picture",
            9 to "recipe_picture",
            10 to "recipe_picture",
            11 to "recipe_picture",
            12 to "recipe_picture",
            13 to "recipe_picture",
            14 to "recipe_picture",
            15 to "recipe_picture",
            16 to "recipe_picture",
            17 to "recipe_picture",
            18 to "pot_au_feu",
            19 to "recipe_picture",
            20 to "recipe_picture",
            21 to "recipe_picture",
            22 to "recipe_picture",
            23 to "recipe_picture",
            24 to "recipe_picture)"
        )

        fun getPictureUri(mealId: Int?) : String {
            return when(mealPicture.keys.contains(mealId)) {
                true -> "@drawable/" + mealPicture[mealId]
                else -> "@drawable/recipe_picture"
            }
        }
    }

}