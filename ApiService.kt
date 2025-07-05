package com.example.projecttracker


import com.example.projecttracker.model.LoginRequest
import com.example.projecttracker.model.Project
import com.example.projecttracker.model.Upload
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/projects")
    fun getProjects(): Call<List<Project>>

    @POST("api/projects")
    fun createProject(@Body project: Project): Call<Project>

    @POST("api/users/login")
    fun login(@Body loginRequest: LoginRequest): Call<User>

    @POST("api/users/register")
    fun register(@Body user: com.example.projecttracker.User): Call<User>


    @POST("api/uploads")
    fun uploadGitLink(@Body upload: Upload): Call<Upload>


    @GET("api/uploads")
    fun getAllUploads(): Call<List<Upload>>

    @GET("api/users")
    fun getAllUsers(): Call<List<User>>






}
