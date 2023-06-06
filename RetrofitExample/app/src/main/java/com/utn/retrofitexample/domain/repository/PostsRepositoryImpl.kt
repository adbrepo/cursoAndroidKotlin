package com.utn.retrofitexample.domain.repository

import android.util.Log
import com.utn.retrofitexample.data.PostsApiService
import com.utn.retrofitexample.domain.model.Comment
import com.utn.retrofitexample.domain.model.Post
import com.utn.retrofitexample.data.entity.PostResponse
import com.utn.retrofitexample.data.entity.toComment
import com.utn.retrofitexample.data.entity.toPost
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsRepositoryImpl : PostsRepository {

    companion object {
        const val TAG = "PostsRepositoryImpl"
    }

    private val apiClient: PostsApiService by lazy {
        // OkHttp client with logging interceptor and api key query param into all requests
        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()

        // Retrofit client with Gson converter (JSON -> data class) and OkHttp client
        val retrofit = Retrofit.Builder()
            .baseUrl(PostsApiService.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(PostsApiService::class.java)
    }

    override suspend fun getPosts(): List<Post>? {
        return try {
            val response = apiClient.getPosts()
            if (response.isSuccessful) {
                response.body()?.map { it.toPost() }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
    }

    override suspend fun getPostById(id: Int): Post? {
        return try {
            val response = apiClient.getPostById(id)
            if (response.isSuccessful) {
                response.body()?.toPost()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
    }

    override suspend fun getCommentsForPost(id: Int): List<Comment>? {
        return try {
            val response = apiClient.getCommentsForPost(id)
            if (response.isSuccessful) {
                response.body()?.map { it.toComment() }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
    }

    override suspend fun createPost(post: PostResponse): Post? {
        return try {
            val response = apiClient.createPost(post)
            if (response.isSuccessful) {
                response.body()?.toPost()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
    }

    override suspend fun deletePost(id: Int) {
        try {
            val response = apiClient.deletePost(id)
            if (!response.isSuccessful) {
                Log.d(TAG, "Error deleting post with id $id")
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
        }
    }
}
