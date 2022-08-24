package com.example.mealprovider

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_meal.*

class AddRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_meal)

        cancel.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

        addButton.setOnClickListener {
            val recipe = recipeName.text
            if (recipe.isNotEmpty()) {
                val recipeName = recipe.toString().toUpperCase()
                val newMealId = App.db.addMeal(recipeName)
                println("Meal created: id [$newMealId] name [$recipe]")
                createAlertDialog(recipeName, this)
            }
        }
    }

    private fun createAlertDialog(recipe: String, activity: AppCompatActivity) {
        activity.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("$recipe a été ajouté")
                setPositiveButton("AJOUTER UN REPAS") { dialog, id ->
                    recipeName.text.clear()
                }
                setNegativeButton("OK") { dialog, id ->
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            }
            // Create the AlertDialog
            builder.create()
            builder.show()
        }
    }
}