package ritesh.bakare420.atgmail.com.demo.templates.SQLLiteCURD

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivitySqladdTaskBinding

class SQLAddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySqladdTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqladdTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val databaseHelper = DatabaseHelper(this)


        binding.btnAdd.setOnClickListener {

            if(binding.ETName.text.isEmpty() and binding.ETDetails.text.isEmpty()) {
                Toast.makeText(this, "Please Add Some Data ", Toast.LENGTH_SHORT).show()
            }
            else {

                val name : String = binding.ETName.text.toString()
                val details : String = binding.ETDetails.text.toString()

                val taskListModel = TaskListModel()

                taskListModel.name = name
                taskListModel.details = details

                databaseHelper.insert(taskListModel)
                Toast.makeText(this, "Data Added Successfully ", Toast.LENGTH_SHORT).show()
            }

        }


    }


}