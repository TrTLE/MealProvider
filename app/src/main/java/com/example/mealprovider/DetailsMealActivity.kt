package com.example.mealprovider

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import com.example.mealprovider.core.PictureProvider
import kotlinx.android.synthetic.main.activity_add_meal.*
import kotlinx.android.synthetic.main.activity_add_meal.cancel
import kotlinx.android.synthetic.main.activity_details_meal.*

class DetailsMealActivity : AppCompatActivity() {

    private val mealId by lazy { intent.getIntExtra("MEAL_ID", -1) }
    private val mealName by lazy { intent.getStringExtra("NAME") }
    private val mealResume by lazy { intent.getStringExtra("RESUME") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_meal)

        cancel.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

        detailsMealTitle.text = mealName
        detailsMealResume.text = mealResume

        val uri = PictureProvider.getPictureUri(mealId)

        System.out.println("MEAL ID :: "+mealId);
        System.out.println("URI :: "+uri);
        System.out.println("PACKAGE_NAME :: "+packageName);
        mealPicture.setImageResource(resources.getIdentifier(uri, null, packageName))
    }

}