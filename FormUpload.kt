package com.example.projecttracker

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projecttracker.model.Upload
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormUpload : Activity() {

    private var projectID: Long = -1L  // Hii inapaswa kupokelewa kutoka Dashboard

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_upload)

        val gitName = findViewById<EditText>(R.id.editgit)
        val submitBtn = findViewById<Button>(R.id.btnSubmit)
        val linkView = findViewById<TextView>(R.id.textGithubLink)

        // Chukua PROJECT_ID kutoka Dashboard (Intent)
        projectID = intent.getLongExtra("PROJECT_ID", -1L)
        if (projectID == -1L) {
            Toast.makeText(this, "Missing Project ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        submitBtn.setOnClickListener {
            val gitLink = gitName.text.toString().trim()

            if (gitLink.isEmpty()) {
                Toast.makeText(this, "Please enter GitHub link", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val upload = Upload(
                projectID = projectID,
                githubLink = gitLink
            )

            RetrofitClient.instance.uploadGitLink(upload).enqueue(object : Callback<Upload> {
                override fun onResponse(call: Call<Upload>, response: Response<Upload>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@FormUpload, "GitHub link uploaded successfully", Toast.LENGTH_SHORT).show()
                        val uploadedLink = response.body()?.githubLink ?: ""
                        linkView.text = uploadedLink
                        Linkify.addLinks(linkView, Linkify.WEB_URLS)
                    } else {
                        Toast.makeText(this@FormUpload, "Server error: Could not upload link", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Upload>, t: Throwable) {
                    Toast.makeText(this@FormUpload, "Network error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
