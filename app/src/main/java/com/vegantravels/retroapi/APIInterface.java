package com.vegantravels.retroapi;


import com.vegantravels.model.Cruises;
import com.vegantravels.model.Guest;
import com.vegantravels.model.GuestDetails;
import com.vegantravels.model.Participant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

    @GET("/api/getCruizeList")
    Call<List<Cruises>> getCruizeList();

 /*   @POST("/api/users")
    Call<User> createUser(@Body User user);*/

    @GET("/api/getGuestList?")
    Call<List<Guest>> getGuestList(@Query("cruizeId") String cruizeId);

    @GET("/api/getGuestDetails?")
    Call<GuestDetails> getGuestDetails(@Query("guestId") String guestId);

    @POST("/api/participant")
    Call<Participant> createUser(@Body Participant participant);


    @POST("/api/guestDetailAdd")
    Call<Guest> guestDetailAdd(@Body Guest guest);



 /*   @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
