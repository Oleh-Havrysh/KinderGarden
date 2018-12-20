package ua.nure.havrysh.kindergarten.rest

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ua.nure.havrysh.kindergarten.model.Announcement
import ua.nure.havrysh.kindergarten.model.Child
import ua.nure.havrysh.kindergarten.model.Group
import ua.nure.havrysh.kindergarten.model.Human
import ua.nure.havrysh.kindergarten.model.LoginRequest
import ua.nure.havrysh.kindergarten.model.LoginResponse
import ua.nure.havrysh.kindergarten.model.Mark

interface Controller {
    
    @POST("login")
    fun login(@Body login: LoginRequest): Deferred<LoginResponse>
    
    @GET("currentUser")
    fun getSelf(): Deferred<Human>
    
    @GET("groups")
    fun getGroups(): Deferred<List<Group>>
    
    @GET("announcements")
    fun getAnnouncements(): Deferred<List<Announcement>>
    
    @GET("groups/{id}")
    fun getGroup(@Path("id") id: String): Deferred<Group>
    
    @GET("/childrenByGroup/{id}")
    fun getChildren(@Path("id") groupId: String): Deferred<List<Child>>
    
    @GET("children/{id}")
    fun getChild(@Path("id") id: String): Deferred<Child>
    
    @GET("humans/{id}")
    fun getHuman(@Path("id") id: String): Deferred<Human>
    
    @GET("announcements/{id}")
    fun getAnnouncement(@Path("id") id: String): Deferred<Announcement>
    
    @POST("saveEvent")
    fun saveAnnouncement(@Body announcement: Announcement): Deferred<ResponseBody>
    
    @GET("marksByChild/{id}")
    fun getMarks(@Path("id") childId: String): Deferred<List<Mark>>
    
    @GET("marks/{id}")
    fun getMark(@Path("id") markId: String): Deferred<Mark>
}
