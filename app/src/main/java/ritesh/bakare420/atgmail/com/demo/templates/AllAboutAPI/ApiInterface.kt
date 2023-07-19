package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @GET("/posts/1")
    suspend fun getUser(): Response<User>


    // function for POST Request
    @POST("/posts")
    suspend fun createPost(
        @Body user: User
    ) : Response<User>


}