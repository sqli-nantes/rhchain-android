package com.sqli.blockchain.rhchain;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.sqli.blockchain.rhchain.blockchain.BlockchainAPI;
import com.sqli.blockchain.rhchain.login.LoginActivity;
import com.sqli.blockchain.rhchain.model.Question;
import com.sqli.blockchain.rhchain.server.Server;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.sqli.blockchain.rhchain.Constants.APP_ID;

/**
 * Created by gunicolas on 20/04/17.
 */

public class RHChainApplication extends Application {

    public List<Question> questions;

    public BlockchainAPI blockchainAPI;
    public Server server;

    RHChainAbstractActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        server = new Server();

        blockchainAPI = new BlockchainAPI(getFilesDir().getAbsolutePath());

        server.questionsService.getQuestions()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(q -> Log.d(APP_ID, showQuestions(q)))
            .subscribeOn(Schedulers.newThread())
            .subscribe(questions -> RHChainApplication.this.questions = questions,
                error -> Utils.showAlertDialog(currentActivity, error.getMessage()),
                () -> currentActivity.startActivity(new Intent(currentActivity, LoginActivity.class)));

    }

    String showQuestions(List<Question> questions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Question q : questions) {
            stringBuilder.append(q.toString());
        }
        return stringBuilder.toString();
    }

    public void setCurrentActivity(RHChainAbstractActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
