package ritesh.bakare420.atgmail.com.demo.templates.AllAboutAPI

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiInterface {

    @GET("/posts/1")
    suspend fun getUser(): Response<User>


    // function for POST Request
    @POST("/posts")
    suspend fun createPost(
        @Body user: User
    ) : Response<User>


    // function for post API for a server that does not accept json
    @FormUrlEncoded
    @POST("/posts")
    suspend fun createURLPost(
        @Field("userId") userId : Int,
        @Field("title") title : String,
        @Field("body") body: String
    ) : Response<User>

    // function for PUT Request
    @PUT("posts/{id}")
    suspend fun putPost(
        @Path("id") id: Int,
        @Body user: User
    ): Response<User>


    // function for PATCH Request
    @PATCH("posts/{id}")
    suspend fun patchPost(
        @Path("id") id:Int,
        @Body user: User
    ) : Response<User>


    // function for DELETE Request
    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Path("id") id: Int,
    ) : Response<User>

}