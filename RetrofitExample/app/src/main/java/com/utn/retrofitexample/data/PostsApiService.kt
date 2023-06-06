package com.utn.retrofitexample.data

import com.utn.retrofitexample.data.entity.CommentResponse
import com.utn.retrofitexample.data.entity.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApiService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<PostResponse>

    @GET("comments")
    suspend fun getCommentsForPost(@Query("postId") id: Int): Response<List<CommentResponse>>

    @POST("posts")
    suspend fun createPost(@Body post: PostResponse): Response<PostResponse>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}
