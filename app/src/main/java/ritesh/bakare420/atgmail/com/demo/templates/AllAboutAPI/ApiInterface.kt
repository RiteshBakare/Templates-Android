package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("/posts/1")
    suspend fun getUser(): Response<User>

}