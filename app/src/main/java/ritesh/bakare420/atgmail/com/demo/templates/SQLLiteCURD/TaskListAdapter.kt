package ritesh.bakare420.atgmail.com.demo.templates.SQLLiteCURD

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ritesh.bakare420.atgmail.com.demo.templates.R


class TaskListAdapter(taskList: List<TaskListModel>) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {


    internal var taskList: List<TaskListModel> = arrayListOf()

    init {
        this.taskList = taskList
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById<TextView>(R.id.tvName)
        var details: TextView = itemView.findViewById(R.id.tvDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.name.text = task.name
        holder.details.text = task.details
    }

}