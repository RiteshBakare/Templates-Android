package ritesh.bakare420.atgmail.com.demo.templates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI.APIActivity
import ritesh.bakare420.atgmail.com.demo.templates.RecyclerView.RecyclerViewActivity
import ritesh.bakare420.atgmail.com.demo.templates.SQLLiteCURD.SQLLiteCRUDActivity
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


        // Dialog and Permissions Activity
        binding.btnDialogAndPermissions.setOnClickListener {
            startActivity(Intent(this,DialogAndPermissions::class.java))
        }

        // Recycler View Activity
        binding.btnRecyclerViews.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }


        // API Activity
        binding.btnRetrofitAPI.setOnClickListener {
            startActivity(Intent(this,APIActivity::class.java))
        }

        //
        binding.btnSQLLite.setOnClickListener {
            startActivity(Intent(this, SQLLiteCRUDActivity::class.java))
        }
    }


}