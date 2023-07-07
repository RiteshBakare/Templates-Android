package ritesh.bakare420.atgmail.com.demo.templates

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityDialogAndPermissionsBinding

class DialogAndPermissions : AppCompatActivity() {


    private lateinit var binding: ActivityDialogAndPermissionsBinding

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

}