package com.vegantravels.retroapi;


import com.vegantravels.model.Cruises;
import com.vegantravels.model.Guest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

    @GET("/api/getCruizeList")
    Call<Cruises> getCruizeList();

 /*   @POST("/api/users")
    Call<User> createUser(@Body User user);*/

    @GET("/api/getGuestList?")
    Call<Guest> getGuestList(@Query("cruizeId") String cruizeId);

 /*   @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
