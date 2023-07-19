package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityApiactivityBinding

class APIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGETAPI.setOnClickListener {
            startActivity(Intent(this, GETAPIActivity::class.java))
        }


    }


}