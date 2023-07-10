package ritesh.bakare420.atgmail.com.demo.templates

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import org.w3c.dom.Text
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityDialogAndPermissionsBinding
import java.util.*
import kotlin.collections.ArrayList

class DialogAndPermissions : AppCompatActivity() {


    private lateinit var binding: ActivityDialogAndPermissionsBinding

    private lateinit var progressBar: Dialog

    // variables for run time permissions
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private var isCameraPermissionsGranted = false
    private var isLocationPermissionGranted = false
    private var isRecordPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogAndPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // run time permissions in Dexter
        binding.btnDexterPermissions.setOnClickListener {
            getPermissions()
        }


        // permission launcher
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

                isCameraPermissionsGranted =
                    permissions[android.Manifest.permission.CAMERA] ?: isCameraPermissionsGranted

                isRecordPermissionGranted =
                    permissions[android.Manifest.permission.RECORD_AUDIO]
                        ?: isRecordPermissionGranted

                isLocationPermissionGranted =
                    permissions[android.Manifest.permission.ACCESS_FINE_LOCATION]
                        ?: isLocationPermissionGranted

            }

        requestPermission()

        binding.btnAlertDialog.setOnClickListener {
            myAlertDialog(
                "my Sample Dialog ", "hello this is a custom message for my Alert Dialog :-) "
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

    // get permissions using dexter
    private fun getPermissions() {
        Dexter.withContext(this)
            .withPermissions(
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.READ_CONTACTS,
                android.Manifest.permission.READ_SMS
            )
            .withListener(object : MultiplePermissionsListener {

                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

                    report.let {
                        if (report!!.areAllPermissionsGranted()) {
                            Toast.makeText(
                                applicationContext,
                                "All Permissions Granted ",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Please Grant All Permissions",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            })
            .withErrorListener {
                Toast.makeText(
                    applicationContext,
                    "Please Grant All Require Permissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    // function for requesting permissions
    fun requestPermission() {

        isCameraPermissionsGranted = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        isRecordPermissionGranted = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest: MutableList<String> = ArrayList()

        if (!isCameraPermissionsGranted) {
            permissionRequest.add(android.Manifest.permission.CAMERA)
        }
        if (!isLocationPermissionGranted) {
            permissionRequest.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isRecordPermissionGranted) {
            permissionRequest.add(android.Manifest.permission.RECORD_AUDIO)
        }

        if (permissionRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionRequest.toTypedArray())
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
            this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this@DialogAndPermissions,
                    "Selected Date: day: $dayOfMonth month: $month year: $year ",
                    Toast.LENGTH_LONG
                ).show()
            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}