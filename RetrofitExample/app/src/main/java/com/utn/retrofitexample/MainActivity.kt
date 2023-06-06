package com.utn.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.utn.retrofitexample.domain.repository.PostsRepository
import com.utn.retrofitexample.domain.repository.PostsRepositoryImpl
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private val postsRepository: PostsRepository = PostsRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val posts = postsRepository.getPosts()
            Log.d(TAG, posts.toString())
        }
    }
}
