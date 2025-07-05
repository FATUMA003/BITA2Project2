package com.example.projecttracker

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.projecttracker.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewProjectsActivity : Activity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project)

        listView = findViewById(R.id.listViewProjects)

        fetchProjectsFromApi()
    }

    private fun fetchProjectsFromApi() {
        RetrofitClient.instance.getProjects().enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                if (response.isSuccessful) {
                    val projects = response.body()
                    if (!projects.isNullOrEmpty()) {
                        val projectList = projects.map {
                            "Project: ${it.projectName}\nGroup: ${it.groupName}\nUser ID: ${it.userID}"
                        }
                        val adapter = ArrayAdapter(this@ViewProjectsActivity, android.R.layout.simple_list_item_1, projectList)
                        listView.adapter = adapter
                    } else {
                        Toast.makeText(this@ViewProjectsActivity, "No projects found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ViewProjectsActivity, "Failed to load projects", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                Toast.makeText(this@ViewProjectsActivity, "Error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
