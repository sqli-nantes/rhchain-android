package com.sqli.blockchain.rhchain.server;

import com.sqli.blockchain.rhchain.model.Question;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by gunicolas on 26/04/17.
 */

public interface QuestionsService {


    @GET("questions/")
    Observable<List<Question>> getQuestions();


}
