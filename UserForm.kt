package com.example.projecttracker

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class UserForm : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form) // badilisha na jina halisi la layout kama: activity_main

        val userName = findViewById<EditText>(R.id.name)
        val userEmail = findViewById<EditText>(R.id.email)
        val userPassword = findViewById<EditText>(R.id.password)
        val userRole = findViewById<EditText>(R.id.role)
        val submitBtn = findViewById<Button>(R.id.btnSubmit)

        submitBtn.setOnClickListener {
            val name = userName.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()
            val role = userRole.text.toString().trim()
            val submit = submitBtn.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()|| role.isEmpty()|| role.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Hapa unaweza kupeleka data kwenye database, server, au Activity nyingine
                Toast.makeText(this, "Project Submitted:\nName: $name\nEmail: $email\nPassword: $password\nRole: $role", Toast.LENGTH_LONG).show()
            }
        }
    }
}
