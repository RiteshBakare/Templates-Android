package ritesh.bakare420.atgmail.com.demo.templates.SQLLiteCURD

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivitySqlliteCrudactivityBinding

class SQLLiteCRUDActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySqlliteCrudactivityBinding

    private lateinit var adapter : TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqlliteCrudactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddTask.setOnClickListener {
            startActivity(Intent(this, SQLAddTaskActivity::class.java))
        }

        binding.sqlRecyclerView.layoutManager = LinearLayoutManager(this)

        val dataBase = DatabaseHelper(this)

        val list = dataBase.getAllTask()

        adapter = TaskListAdapter(list)

        binding.sqlRecyclerView.adapter = adapter

        adapter.notifyDataSetChanged()

    }

    override fun onRestart() {
        super.onRestart()
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}