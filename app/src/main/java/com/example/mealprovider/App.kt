package com.example.mealprovider

import android.app.Application
import com.example.mealprovider.core.Generator
import com.example.mealprovider.model.database.RecipeDb
import com.example.mealprovider.model.database.RecipeDbHelper

class App : Application(){

    companion object{
        lateinit var instance : App
        lateinit var db : RecipeDb
        lateinit var dbHelper : RecipeDbHelper
        lateinit var generator: Generator
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dbHelper = RecipeDbHelper(instance)
        dbHelper.readableDatabase
        db = RecipeDb(dbHelper)
        generator = Generator()
    }

}