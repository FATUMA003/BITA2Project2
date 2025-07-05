package com.example.projecttracker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.projecttracker.model.Upload
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewUploadActivity : Activity() {

    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_upload)

        tableLayout = findViewById(R.id.tableUploads)

        // Call API
        RetrofitClient.instance.getAllUploads().enqueue(object : Callback<List<Upload>> {
            override fun onResponse(call: Call<List<Upload>>, response: Response<List<Upload>>) {
                if (response.isSuccessful && response.body() != null) {
                    val uploads = response.body()!!
                    for (upload in uploads) {
                        addRow(upload)
                    }
                } else {
                    Toast.makeText(this@ViewUploadActivity, "Failed to fetch uploads", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Upload>>, t: Throwable) {
                Toast.makeText(this@ViewUploadActivity, "Error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addRow(upload: Upload) {
        val row = TableRow(this)

        val idView = TextView(this).apply {
            text = upload.id.toString()
            setPadding(8, 8, 8, 8)
        }

        val linkView = TextView(this).apply {
            text = upload.githubLink
            setPadding(8, 8, 8, 8)
            setTextColor(resources.getColor(android.R.color.holo_blue_dark))
            setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upload.githubLink))
                startActivity(browserIntent)
            }
        }

        row.addView(idView)
        row.addView(linkView)

        tableLayout.addView(row)
    }
}
