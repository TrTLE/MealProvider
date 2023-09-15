package com.example.mealprovider

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mealprovider.databinding.ActivityMainBinding
import com.example.mealprovider.model.database.Meal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var actualMeal: Meal
    private lateinit var mBinder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        mBinder = ActivityMainBinding.inflate(layoutInflater)
        // next meal button listener
        nextMealbutton.setOnClickListener {
            actualMeal = App.generator.getNextMeal()
            nextMealTextView.text = actualMeal.NAME
        }
        // add next button listener
        fab.setOnClickListener { startActivity(Intent(this, AddRecipeActivity::class.java)) }
        // picker button listener
        pickerBtn.setOnClickListener { startActivity(Intent(this, PickerActivity::class.java)) }
        // next meal text on click listener
        nextMealTextView.setOnClickListener {
            if (nextMealTextView.text.isNotBlank()) {
                val myIntent = Intent(this, DetailsMealActivity::class.java)
                myIntent.putExtra("MEAL_ID", actualMeal.ID)
                myIntent.putExtra("NAME", actualMeal.NAME)
                myIntent.putExtra("RESUME", actualMeal.RESUME)
                startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.mealList -> {
                startActivity(Intent(this, MealListActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}