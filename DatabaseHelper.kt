package com.example.projecttracker.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "project_tracker", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fullName TEXT,
                email TEXT UNIQUE,
                password TEXT,
                role TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun insertUser(fullName: String, email: String, password: String, role: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("fullName", fullName)
            put("email", email)
            put("password", password)
            put("role", role)
        }
        return db.insert("users", null, values) != -1L
    }

    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", arrayOf(email, password))
        val success = cursor.count > 0
        cursor.close()
        return success
    }

    fun getUserRole(email: String, password: String): String? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT role FROM users WHERE email=? AND password=?", arrayOf(email, password))
        var role: String? = null
        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndexOrThrow("role"))
        }
        cursor.close()
        return role
    }
}
