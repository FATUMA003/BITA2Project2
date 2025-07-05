package com.example.projecttracker

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.projecttracker.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormActivity : Activity() {

    private lateinit var groupNameEditText: EditText
    private lateinit var projectNameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var submitBtn: Button

    // Badilisha hapa na userID halisi wa mtu aliye logged in
    private val loggedInUserId: Long = 1L

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        groupNameEditText = findViewById(R.id.name)
        projectNameEditText = findViewById(R.id.project)
        descriptionEditText = findViewById(R.id.editTextComment)
        submitBtn = findViewById(R.id.btnSubmitComment)

        submitBtn.setOnClickListener {
            val group = groupNameEditText.text.toString().trim()
            val project = projectNameEditText.text.toString().trim()
            val desc = descriptionEditText.text.toString().trim()

            if (group.isEmpty() || project.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                addProject(group, project, desc)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addProject(group: String, projectName: String, description: String) {
        // Pata tarehe ya leo kwa format yyyy-MM-dd
        val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

        // Tengeneza project object kwa kuzingatia model ya backend (hapa tunatumia userID badala ya User object)
        val project = Project(
            userID = loggedInUserId,
            groupName = group,
            projectName = projectName,
            description = description,
            date = currentDate
        )

        // Tuma project kwa backend kupitia Retrofit
        RetrofitClient.instance.createProject(project).enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@FormActivity, "Project added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@FormActivity, "Server error: Could not add project", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                Toast.makeText(this@FormActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
