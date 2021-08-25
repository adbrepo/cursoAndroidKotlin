package com.utn.coroutinesexample.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.utn.coroutinesexample.R
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()



        val parentJob = Job()
        val scope = CoroutineScope(Dispatchers.Default + parentJob)


        scope.launch {
            task1()
            task2()
            task3()
        }

        task4()

        scope.launch {
            val one = async { fetchDataFromServerOne() }
            val two = async { fetchDataFromServerTwo() }
            Log.d("Test", "The sum is ${one.await() + two.await()}")
        }

//        scope.launch {
//            task1()
//        }
//        scope.launch {
//            task2()
//        }
//        scope.launch {
//            task3()
//        }



    }

}


suspend fun task1 (){

    delay(3000);
    Log.d("Test","tarea1")
}

suspend fun task2 (){

    delay(30);
    Log.d("Test","tarea2")
}

suspend fun task3 (){

    delay(3000);
    Log.d("Test","tarea3")
}

 fun task4 (){

    Log.d("Test","tarea4")
}

private suspend fun fetchDataFromServerOne(): Int {
    Log.d("Test", "fetchDataFromServerOne()")
    delay(1000)
    return 1
}

private suspend fun fetchDataFromServerTwo(): Int {
    Log.d("Test", "fetchDataFromServerTwo()")
    delay(1000)
    return 2
}
