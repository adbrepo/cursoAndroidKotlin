package com.utn.retrofitexample.domain.repository

import com.utn.retrofitexample.domain.model.Comment
import com.utn.retrofitexample.domain.model.Post
import com.utn.retrofitexample.data.entity.PostResponse

interface PostsRepository {

    suspend fun getPosts(): List<Post>?

    suspend fun getPostById(id: Int): Post?

    suspend fun getCommentsForPost(id: Int): List<Comment>?

    suspend fun createPost(post: PostResponse): Post?

    suspend fun deletePost(id: Int)
}
