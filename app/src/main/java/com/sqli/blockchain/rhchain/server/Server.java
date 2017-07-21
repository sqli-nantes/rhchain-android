package com.sqli.blockchain.rhchain.server;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sqli.blockchain.rhchain.Constants.IP_ADMIN;
import static com.sqli.blockchain.rhchain.Constants.PORT_ADMIN;

/**
 * Created by mcame on 17/02/17.
 * //http://square.github.io/retrofit/
 */


public class Server {

    private final String baseUrl = "http://" + IP_ADMIN + ":" + PORT_ADMIN + "/api/";
    public UsersService registrationService;
    public QuestionsService questionsService;

    public Server() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

        this.registrationService = retrofit.create(UsersService.class);
        this.questionsService = retrofit.create(QuestionsService.class);

    }

}
