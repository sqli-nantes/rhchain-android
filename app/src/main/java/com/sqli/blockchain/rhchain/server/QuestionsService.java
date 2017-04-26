package com.sqli.blockchain.rhchain.server;

import com.sqli.blockchain.rhchain.model.Question;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by gunicolas on 26/04/17.
 */

public interface QuestionsService {


    @GET("questions/")
    Observable<List<Question>> getQuestions();


}
