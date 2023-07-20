package ritesh.bakare420.atgmail.com.demo.templates.SQLLiteCURD

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME: String = "task"
        private val DATABASE_VERSION: Int = 1
        private val TABLE_NAME: String = "tasklist"
        private val ID: String = "id"
        private val TASK_NAME: String = "taskname"
        private val TASK_DETAILS: String = "taskdetails"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $TASK_NAME TEXT, $TASK_DETAILS TEXT);"

        db?.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)

    }

    // SQL lite get all data function
    @SuppressLint("Range")
    fun getAllTask(): ArrayList<TaskListModel> {
        val taskList = ArrayList<TaskListModel>()

        val db = writableDatabase
        val selectQuery = "SELECT *FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val task = TaskListModel()
                    task.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    task.name = cursor.getString(cursor.getColumnIndex(TASK_NAME))
                    task.details = cursor.getString(cursor.getColumnIndex(TASK_DETAILS))
                    taskList.add(task)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return taskList
    }

    // SQL lite insert
    fun insert(task: TaskListModel): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME, task.name)
        values.put(TASK_DETAILS, task.details)

        val _success = db.insert(TABLE_NAME, null, values)

        db.close()
        return (Integer.parseInt("$_success") != -1)
    }


    // Select the data of particular id
    @SuppressLint("Range")
    fun getTask(_id: Int): TaskListModel {
        val task = TaskListModel()
        val db = writableDatabase

        val selectQuery = "SELECT *FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        task.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        task.name = cursor.getString(cursor.getColumnIndex(TASK_NAME))
        task.details = cursor.getString(cursor.getColumnIndex(TASK_DETAILS))
        cursor.close()

        return task
    }


    // function to Delete Task
    fun deleteTask(_id: Int): Boolean {

        val db = this.writableDatabase

        val _sucess = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()

        db.close()

        return Integer.parseInt("$_sucess") != -1
    }


    fun updateTask(task: TaskListModel) : Boolean {

        val db = this.writableDatabase

        val value = ContentValues()

        value.put(TASK_NAME,task.name)
        value.put(TASK_DETAILS,task.details)

        val _success = db.update(TABLE_NAME,value, "$ID=?", arrayOf(task.id.toString())).toLong()

        db.close()

        return Integer.parseInt("$_success") != -1
    }

}
