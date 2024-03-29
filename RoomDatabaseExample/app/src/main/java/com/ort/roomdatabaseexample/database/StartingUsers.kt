package com.ort.roomdatabaseexample.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ort.roomdatabaseexample.R
import com.ort.roomdatabaseexample.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingUsers(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers", "Pre-populating database...")
            fillWithStartingUsers(context)
        }
    }

    /**
     * Pre-populate database with hard-coded users
     */
    private fun fillWithStartingUsers(context: Context) {
        val users = listOf(
            User(0, "John", "john@gmail.com"),
            User(0, "Jane", "jane@gmail.com"),
            User(0, "Matt", "matt@gmail.com"),
            User(0, "Jeff", "jeff@gmail.com")
        )
        val dao = AppDatabase.getInstance(context)?.userDao()

        users.forEach {
            dao?.insertUser(it)
        }
    }

    /**
     * Pre-populate database with users from a Json file
     */
    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabase.getInstance(context)?.userDao()

        try {
            val users = loadJSONArray(context, R.raw.users)
            for (i in 0 until users.length()) {
                val item = users.getJSONObject(i)
                val user = User(
                    id = 0,
                    name = item.getString("name"),
                    email = item.getString("email")
                )

                dao?.insertUser(user)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }

    /**
     * Utility to load a JSON array from the raw folder
     */
    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}
