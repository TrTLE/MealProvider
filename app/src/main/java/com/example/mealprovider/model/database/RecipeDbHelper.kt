package com.example.mealprovider.model.database

import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import com.example.mealprovider.App
import org.jetbrains.anko.db.*
import java.io.File
import java.io.FileOutputStream

class RecipeDbHelper (ctx : Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
    DB_NAME, null,
    DB_VERSION
) {

    companion object{
        val DB_NAME = "recette"
        val ASSETS_PATH = "database"
        val DB_VERSION = 1
        val instance by lazy { RecipeDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        throw NotImplementedError();
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        throw NotImplementedError();
    }

    private fun installDatabaseFromAssets() {
        println("RecipeDbHelper installDatabaseFromAssets: START")
        val inputStream = App.instance.assets.open("$ASSETS_PATH/$DB_NAME.db")
        try {
            val outputFile = File(App.instance.getDatabasePath(DB_NAME).path)
            val outputStream = FileOutputStream(outputFile)

            inputStream.copyTo(outputStream)
            inputStream.close()

            outputStream.flush()
            outputStream.close()
        } catch (exception: Throwable) {
            throw RuntimeException("The $DB_NAME database couldn't be installed.", exception)
        }
        println("RecipeDbHelper installDatabaseFromAssets: END")
    }

    private val preferences: SharedPreferences = App.instance.getSharedPreferences(
        "${App.instance.packageName}.database_versions",
        Context.MODE_PRIVATE
    )

    private fun installedDatabaseIsOutdated(): Boolean {
        println("RecipeDbHelper installedDatabaseIsOutdated: CALL")
        return preferences.getInt(DB_NAME, 0) < DB_VERSION
    }

    private fun writeDatabaseVersionInPreferences() {
        println("RecipeDbHelper writeDatabaseVersionInPreferences: START")
        preferences.edit().apply {
            putInt(DB_NAME, DB_VERSION)
            apply()
        }
        println("RecipeDbHelper writeDatabaseVersionInPreferences: END")
    }

    @Synchronized
    private fun installOrUpdateIfNecessary() {
        println("RecipeDbHelper installOrUpdateIfNecessary: START")
        if (installedDatabaseIsOutdated()) {
            App.instance.deleteDatabase(DB_NAME)
            installDatabaseFromAssets()
            writeDatabaseVersionInPreferences()
        }
        println("RecipeDbHelper installOrUpdateIfNecessary: END")
    }

    override fun getReadableDatabase(): SQLiteDatabase {
        println("RecipeDbHelper getReadableDatabase: START")
        installOrUpdateIfNecessary()
        println("RecipeDbHelper getReadableDatabase: END")
        return super.getReadableDatabase()
    }

    /*override fun getWritableDatabase(): SQLiteDatabase {
        throw RuntimeException("The $DB_NAME database is not writable.")
    }*/

}