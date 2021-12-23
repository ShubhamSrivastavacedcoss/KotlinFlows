package com.example.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data1 = flowOf(1, 2, 3, 4, 5).flowOn(Dispatchers.IO)
        val data2 = listOf(10, 46, 88, 15, 98).asFlow().flowOn(Dispatchers.IO)
        val data3 = flowOf(1, 2, 3, 4, 5,6).flowOn(Dispatchers.IO)


        runBlocking {

            data3.filter { value ->
                value % 2 == 0
            }.collect {
                Log.i("TAG1", "onCreate: $it")
            }


            getData1().map { value ->
                value * value
            }.collect {
                // Log.i("TAG1", "onCreate: $it")
            }
            data1.collect {
                //Log.i("TAG", "onCreate: $it")
            }

            data2.collect {
                //  Log.i("TAG", "onCreate: $it")
            }
        }

    }


    fun getData1(): Flow<Int> = flow {
        for (i in 1..5) {
            delay(1000L)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)


}