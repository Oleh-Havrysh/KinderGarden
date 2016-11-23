package ua.nure.havrysh.kindergarten.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ua.nure.havrysh.kindergarten.model.Announcement;
import ua.nure.havrysh.kindergarten.model.Child;
import ua.nure.havrysh.kindergarten.model.Group;
import ua.nure.havrysh.kindergarten.model.Human;
import ua.nure.havrysh.kindergarten.model.Mark;
import ua.nure.havrysh.kindergarten.model.Message;

/**
 * Created by Oleg on 16.10.2016.
 */

public interface Controller {
    @GET("groups")
    Call<List<Group>> getGroups();

    @GET("groups/{id}")
    Call<Group> getGroup(@Path("id") long id);

    @GET("groups/{id}/children")
    Call<List<Child>> getChildren(@Path("id") long groupId);

    @GET("children")
    Call<List<Child>> getChildren();

    @GET("children/{id}")
    Call<Child> getChild(@Path("id") long id);

    @GET("humans/{id}")
    Call<Human> getHuman(@Path("id") long id);

    @GET("announcements")
    Call<List<Announcement>> getAnnouncements();

    @GET("announcements/{id}")
    Call<Announcement> getAnnouncement(@Path("id") long id);

    @GET("marks/{childId}")
    Call<List<Mark>> getMarks(@Path("childId") long childId);

    @GET("messages/{from}/{to}")
    Call<List<Message>> getMessages(@Path("from") long from, @Path("to") long to);

}
