package com.example.projecttracker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
class Admin : Activity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var logoutButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin) // layout yako ya dashboard

        // Vuta buttons
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        logoutButton = findViewById(R.id.logoutButton)

        button1.setOnClickListener {
            // Fungua activity ya ku-add project (kama ipo)
            val intent = Intent(this, UserForm::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, ViewUsersActivity::class.java)
            startActivity(intent)

        }
        button3.setOnClickListener {
            // Fungua activity ya ku-add project (kama ipo)
            val intent = Intent(this, ViewUploadActivity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            // Fungua activity ya ku-add project (kama ipo)
            val intent = Intent(this, ViewProjectsActivity::class.java)
            startActivity(intent)
        }


        logoutButton.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Kama activity_form ni Activity ya kweli, weka class yake kivyake
}
