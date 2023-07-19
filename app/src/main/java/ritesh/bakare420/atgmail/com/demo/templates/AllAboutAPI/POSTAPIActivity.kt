package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityPostapiactivityBinding
import java.io.IOException

class POSTAPIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostapiactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostapiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {

            val response = try {
                val user = User(null, "this is user body ", "this is title ", 10)
                RetrofitInstance.api.createPost(user);

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
                    Snackbar.make(
                        binding.root,
                        "data: ${response.code()} ",
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .show()
                    Log.d("MyAPI", "HTTP Code: ${response.code()} ")

                    Log.d("MyAPI", "data: ${response.body()!!.id.toString()}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.body}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.title}  ")
                    Log.d("MyAPI", "data: ${response.body()!!.userId}  ")

                }
            }


            //---------------------------------------------------------------------------------
            // this function is for sending the data to a server that does not accept json

            GlobalScope.launch(Dispatchers.IO) {

                val response = try {

                    RetrofitInstance.api.createURLPost(10,"URL Title ","URL Body");

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
                        Toast.makeText(applicationContext, "Code: ${response.code()} ", Toast.LENGTH_SHORT).show()
                        Log.d("MyAPI","-------------------------------")
                        Log.d("MyAPI", "HTTP Code: ${response.code()} ")

                        Log.d("MyAPI", "data: ${response.body()!!.id.toString()}  ")
                        Log.d("MyAPI", "data: ${response.body()!!.body}  ")
                        Log.d("MyAPI", "data: ${response.body()!!.title}  ")
                        Log.d("MyAPI", "data: ${response.body()!!.userId}  ")

                    }
                }


            }


        }

    }

}