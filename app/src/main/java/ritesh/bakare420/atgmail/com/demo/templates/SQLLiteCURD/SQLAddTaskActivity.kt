package ritesh.bakare420.atgmail.com.demo.templates.SQLLiteCURD

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivitySqladdTaskBinding

class SQLAddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySqladdTaskBinding

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqladdTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)


        binding.btnAdd.setOnClickListener {

            if (binding.ETName.text.isEmpty() and binding.ETDetails.text.isEmpty()) {
                Toast.makeText(this, "Please Add Some Data ", Toast.LENGTH_SHORT).show()
            } else {

                val name: String = binding.ETName.text.toString()
                val details: String = binding.ETDetails.text.toString()

                val taskListModel = TaskListModel()

                taskListModel.name = name
                taskListModel.details = details

                val flag = databaseHelper.insert(taskListModel)

                if (flag) {
                    Toast.makeText(this, "Data Added Successfully ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fail to Add Data  ", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.btnDeleteTask.setOnClickListener {
            val id = binding.ETIdForDelete.text.toString()

            if (id.isEmpty()) {
                Toast.makeText(this, "Enter ID to Delete", Toast.LENGTH_SHORT).show()
            } else {
                deleteTask(id.toInt())
                Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnUpdateTask.setOnClickListener {

            val id = binding.ETIdForUpdate.text.toString()

            if (id.isEmpty()) {
                Toast.makeText(this, "Please Enter a ID for Update", Toast.LENGTH_SHORT).show()
            } else {

                val task = databaseHelper.getTask(id.toInt())

                binding.previousName.text = task.name
                binding.previousDetails.text = task.details


                if (binding.ETName.text.isEmpty() and binding.ETDetails.text.isEmpty()) {
                    Toast.makeText(this, "Please Have a details to update", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    val newTask = TaskListModel()

                    newTask.name = binding.ETName.text.toString()
                    newTask.details = binding.ETDetails.text.toString()

                    val flag = databaseHelper.updateTask(newTask)

                    if (flag) {
                        Toast.makeText(this, "Data Updated  Successfully", Toast.LENGTH_SHORT)
                            .show()
                        binding.updatedName.text = newTask.name
                        binding.updatedDetails.text = newTask.details
                    } else {
                        Toast.makeText(this, "Fail to  Delete Data", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    private fun deleteTask(id: Int) {
        val flag = databaseHelper.deleteTask(id)

        if (flag) {
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Fail to delete Data", Toast.LENGTH_SHORT).show()
        }

    }


}