package com.example.projecttracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Dashboard : Activity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        logoutButton = findViewById(R.id.logoutButton)

        button1.setOnClickListener {

            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val projectID: Long = 1L

            val intent = Intent(this, FormUpload::class.java)
            intent.putExtra("PROJECT_ID", projectID)
            startActivity(intent)
            Toast.makeText(this, "Upload Project clicked", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}
