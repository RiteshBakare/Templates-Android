package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityApiactivityBinding
import java.io.IOException

class APIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGETAPI.setOnClickListener {
            startActivity(Intent(this, GETAPIActivity::class.java))
        }

        binding.btnPOSTAPI.setOnClickListener {
            startActivity(Intent(this, POSTAPIActivity::class.java))
        }

        // the PUT, PATCH and DELETE code are written here


        // for PUT Request
        putDataRequest()

        // for PATCH Request
        patchDataRequest()


        // for DELETE Request
        deleteDataRequest()


    }

    private fun deleteDataRequest() {
        GlobalScope.launch(Dispatchers.IO) {

            val response = try {

                RetrofitInstance.api.deletePost(10)

            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "error: ${e.message()}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "error: ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Code: ${response.code()} ",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("MyAPI", "-------------------------------")
                    Log.d("MyAPI", "-------------DELETE------------")
                    Log.d("MyAPI", "HTTP Code: ${response.code()} ")
                    Log.d("MyAPI", "data: ${response.body()!!.id.toString()}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.body}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.title}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.userId}  ")
                    Log.d("MyAPI", "-------------------------------")


                }
            }
        }
    }

    private fun patchDataRequest() {
        GlobalScope.launch(Dispatchers.IO) {

            val response = try {

                val user= User(10,"patch Body ","patch title",10)
                RetrofitInstance.api.patchPost(10,user)

            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "error: ${e.message()}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "error: ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Code: ${response.code()} ",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("MyAPI", "-------------------------------")
                    Log.d("MyAPI", "--------------PATCH------------")
                    Log.d("MyAPI", "HTTP Code: ${response.code()} ")
                    Log.d("MyAPI", "data: ${response.body()!!.id.toString()}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.body}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.title}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.userId}  ")
                    Log.d("MyAPI", "-------------------------------")


                }
            }
        }
    }

    private fun putDataRequest() {
        GlobalScope.launch(Dispatchers.IO) {

            val response = try {

                val user= User(10,"put Body ","put title",10)
                RetrofitInstance.api.putPost(10,user)

            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "error: ${e.message()}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "error: ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Code: ${response.code()} ",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("MyAPI", "-------------------------------")
                    Log.d("MyAPI", "--------------PUT--------------")
                    Log.d("MyAPI", "HTTP Code: ${response.code()} ")
                    Log.d("MyAPI", "data: ${response.body()!!.id.toString()}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.body}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.title}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.userId}  ")
                    Log.d("MyAPI", "-------------------------------")
                }

            }
        }
    }


}