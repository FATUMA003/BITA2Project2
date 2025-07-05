package com.example.projecttracker

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewUsersActivity : Activity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_users)

        listView = findViewById(R.id.listViewUsers)

        fetchUsersFromApi()
    }

    private fun fetchUsersFromApi() {
        RetrofitClient.instance.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        // Tumia ArrayAdapter ku-display list ya user fullName (au email)
                        val userNames = users.map { "${it.fullName} (${it.email})" }
                        val adapter = ArrayAdapter(this@ViewUsersActivity, android.R.layout.simple_list_item_1, userNames)
                        listView.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@ViewUsersActivity, "Failed to load users", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@ViewUsersActivity, "Error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
