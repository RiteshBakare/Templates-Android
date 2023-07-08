package ritesh.bakare420.atgmail.com.demo.templates

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityDialogAndPermissionsBinding
import java.util.*

class DialogAndPermissions : AppCompatActivity() {


    private lateinit var binding: ActivityDialogAndPermissionsBinding

    private lateinit var progressBar: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogAndPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAlertDialog.setOnClickListener {
            myAlertDialog(
                "my Sample Dialog ",
                "hello this is a custom message for my Alert Dialog :-) "
            )
        }

        // initializing the Progress Bar
        progressBar = Dialog(this)

        binding.btnProgressBar.setOnClickListener {
            showMyProgressBar()

            android.os.Handler(Looper.getMainLooper()).postDelayed({
                dismissMyProgressBar()
            }, 1000)

        }


        binding.btnCustomDialog.setOnClickListener {
            myCustomDialog("Hello Custom Dialog Title ", "This is message from custom dialog :-) ")
        }

        binding.btnCustomProgressBar.setOnClickListener {
            myCustomProgressBar()
        }

        binding.DatePickerDialog.setOnClickListener {
            myDatePickerDialog()
        }
    }

    // function for having Alert Dialog
    private fun myAlertDialog(title: String, message: String) {

        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(R.drawable.android)

        builder.setPositiveButton(" OK ") { dialog, which ->
            Toast.makeText(this, "OK Button Pressed ", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton(" Cancel ") { dialog, which ->
            Toast.makeText(this, "CANCEL Button Pressed ", Toast.LENGTH_LONG).show()
        }

        builder.setNeutralButton(" Neutral ") { dialog, which ->
            Toast.makeText(this, "NEUTRAL Button Pressed ", Toast.LENGTH_LONG).show()
        }

        builder.create().show()
    }

    // function for having a Progress Bar


    private fun showMyProgressBar() {
        progressBar.setContentView(R.layout.my_progress_bar)
        progressBar.show()
    }

    // function for dismissing progress Bar
    private fun dismissMyProgressBar() = progressBar.dismiss()

    // function for having Custom Dialog
    private fun myCustomDialog(title: String, message: String) {
        val builder = Dialog(this)
        builder.setContentView(R.layout.my_custom_dialog)

        val dialogTitle = builder.findViewById<TextView>(R.id.tvTitle)
        dialogTitle.text = title

        val dialogMessage = builder.findViewById<TextView>(R.id.tvMessage)
        dialogMessage.text = message

        val btnOK = builder.findViewById<Button>(R.id.btnOne)
        btnOK.text = "OK"
        btnOK.setOnClickListener {
            Toast.makeText(applicationContext, "OK Btn Pressed", Toast.LENGTH_LONG).show()
            builder.dismiss()
        }

        val btnCancel = builder.findViewById<Button>(R.id.btnTwo)
        btnCancel.text = "CANCEL"
        btnCancel.setOnClickListener {
            Toast.makeText(applicationContext, "CANCEL Btn Pressed", Toast.LENGTH_LONG).show()
            builder.dismiss()
        }

        builder.setCancelable(false)

        builder.create()
        builder.show()
    }

    // function for having custom Progress Bar
    fun myCustomProgressBar() {
        binding.customProgressBar.progress = binding.customProgressBar.progress + 10
    }

    // function for having date picker dialog
    private fun myDatePickerDialog() {

        val myCalendar = Calendar.getInstance()
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val month = myCalendar.get(Calendar.MONTH)
        val year = myCalendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this@DialogAndPermissions,
                    "Selected Date: day: $dayOfMonth month: $month year: $year ",
                    Toast.LENGTH_LONG
                ).show()
            },
            year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}