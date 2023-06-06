package com.utn.retrofitexample.data.entity

import com.utn.retrofitexample.domain.model.Comment
import com.utn.retrofitexample.domain.model.Post

fun PostResponse.toPost(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)

fun CommentResponse.toComment(): Comment = Comment(
    postId = postId,
    id = id,
    name = name,
    email = email,
    body = body
)
