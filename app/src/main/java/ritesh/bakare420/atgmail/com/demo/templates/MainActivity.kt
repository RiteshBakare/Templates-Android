package ritesh.bakare420.atgmail.com.demo.templates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Coroutines Activity
        binding.btnCoroutines.setOnClickListener {
            startActivity(Intent(this,CoroutinesActivity::class.java))
        }


    }


}