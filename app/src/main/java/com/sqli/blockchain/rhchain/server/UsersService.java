package com.sqli.blockchain.rhchain.server;

import com.sqli.blockchain.rhchain.model.Question;
import com.sqli.blockchain.rhchain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


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
