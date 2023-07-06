package ritesh.bakare420.atgmail.com.demo.templates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.combineTransform
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityCoroutinesBinding
import kotlin.concurrent.thread

class CoroutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnUpdateCounter.setOnClickListener {
            Log.d("ThreadCheck", "Thread name: ${Thread.currentThread().name}")
            binding.tvCounter.text = "${binding.tvCounter.text.toString().toInt() + 1}"
        }

        // this task will take time hence it freezes our UI
        binding.btnLongTask.setOnClickListener {
            longExecutingTask()
        }

        binding.btnLongTaskWithThread.setOnClickListener {
            taskWithThread()
        }

        binding.btnCoroutinesInfo.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                Log.d("ThreadCheck", "1) => Thread name: ${Thread.currentThread().name}")
            }

            GlobalScope.launch(Dispatchers.Main) {
                Log.d("ThreadCheck", "2) => Thread name: ${Thread.currentThread().name}")
            }

            MainScope().launch(Dispatchers.Default) {
                Log.d("ThreadCheck", "3) => Thread name: ${Thread.currentThread().name}")
            }

        }

        binding.btnCoroutinesSuspend.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                task1()
            }

            CoroutineScope(Dispatchers.Main).launch {
                task2()
            }

        }

        binding.btnCoroutinesBuilders.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                printFBFollowersUsingLaunch()
            }

            CoroutineScope(Dispatchers.IO).launch {
                printFBFollowersUsingAsync()
            }


        }


    }

    private suspend fun printFBFollowersUsingAsync() {
        val job = CoroutineScope(Dispatchers.IO).async {
            getFBFollowers()
        }
        Log.d("CoroutinesBuilders", "fb followers: ${job.await()} using async ")
    }

    private suspend fun printFBFollowersUsingLaunch() {
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFBFollowers()
        }
        job.join()
        Log.d("CoroutinesBuilders", "fb followers: $fbFollowers using launch")
    }

    private suspend fun getFBFollowers(): Int {
        delay(1000)
        return 120
    }


    private suspend fun task1() {
        Log.d("MyCoroutines", "Starting Task 1 and thread is ${Thread.currentThread().name}")
        yield()
        Log.d("MyCoroutines", "Ending Task 1 and thread is ${Thread.currentThread().name} ")
    }

    private suspend fun task2() {
        Log.d("MyCoroutines", "Starting Task 2 and thread is ${Thread.currentThread().name} ")
        yield()
        Log.d("MyCoroutines", "Ending Task 2 and thread is ${Thread.currentThread().name} ")
    }

    private fun longExecutingTask() {
        for (i in 1..10000000000L) {
            // some time taking task
        }
    }

    private fun taskWithThread() {
        thread(start = true) {
            for (i in 1..10000000000L) {
                // some time taking task
            }
            Log.d("ThreadCheck", "Thread name: ${Thread.currentThread().name}")
        }

    }

}