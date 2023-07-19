package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import ritesh.bakare420.atgmail.com.demo.templates.R
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityGetapiactivityBinding
import java.io.IOException

class GETAPIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetapiactivityBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetapiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvId.visibility = View.GONE
            tvBody.visibility = View.GONE
            tvTitle.visibility = View.GONE
            tvUserId.visibility = View.GONE
        }

        GlobalScope.launch(Dispatchers.IO) {

            val response: Response<User> = try {
                RetrofitInstance.api.getUser();
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
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE

                        tvId.text = "ID: ${response.body()!!.id} "
                        tvBody.text = "Body:  ${response.body()!!.body}"
                        tvTitle.text = "Title: ${response.body()!!.title}"
                        tvUserId.text = "user ID:  ${response.body()!!.userId}"
                    }
                }
            }

        }

    }

}