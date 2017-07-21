package com.sqli.blockchain.rhchain.server;

import com.sqli.blockchain.rhchain.model.Question;
import com.sqli.blockchain.rhchain.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by mcame on 17/02/17.
 */

public interface UsersService {

    @GET("users/")
    Observable<List<User>> getListeUsers();

    @GET("users/{mail}")
    Observable<User> getUser(@Path("mail") String mail);

    @GET("users/{mail}/adress")
    Observable<String> getAdress(@Path("mail") String mail);

    @POST("users/")
    Observable<User> createUser(@Body User user);

}
