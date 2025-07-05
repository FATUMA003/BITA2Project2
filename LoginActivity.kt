package com.example.projecttracker

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projecttracker.model.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : Activity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.editEmailLogin)
        val passwordEditText = findViewById<EditText>(R.id.editPasswordLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val userEmail = emailEditText.text.toString().trim()
            val userPassword = passwordEditText.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(userEmail, userPassword)

            RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val loggedInUser = response.body()
                        if (loggedInUser != null) {
                            sharedPreferences.edit().apply {
                                putString("email", loggedInUser.email)
                                putString("role", loggedInUser.role)
                                putLong("userID", loggedInUser.id?.toLong() ?: -1L)
                                apply()
                            }

                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()

                            if (loggedInUser.role.equals("ADMIN", ignoreCase = true)) {
                                startActivity(Intent(this@LoginActivity, Admin::class.java))
                            } else {
                                startActivity(Intent(this@LoginActivity, Dashboard::class.java))
                            }
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "User data not found", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Wrong email or password", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Network Error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
