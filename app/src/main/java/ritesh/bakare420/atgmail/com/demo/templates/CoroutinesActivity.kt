package ritesh.bakare420.atgmail.com.demo.templates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            Log.d("ThreadCheck","Thread name: ${Thread.currentThread().name}")
            binding.tvCounter.text = "${binding.tvCounter.text.toString().toInt()+1}"
        }

        // this task will take time hence it freezes our UI
        binding.btnLongTask.setOnClickListener {
            longExecutingTask()
        }

        binding.btnLongTaskWithThread.setOnClickListener {
            taskWithThread()
        }



    }


    private fun longExecutingTask() {
        for( i in 1..10000000000L) {
            // some time taking task
        }
    }

    private fun taskWithThread() {
        thread(start = true) {
            for( i in 1..10000000000L) {
                // some time taking task
            }
            Log.d("ThreadCheck","Thread name: ${Thread.currentThread().name}")
        }

    }

}