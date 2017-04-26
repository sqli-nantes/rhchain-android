package com.sqli.blockchain.rhchain;

import android.content.Intent;
import android.util.Log;

import com.sqli.blockchain.android_geth.EthereumApplication;
import com.sqli.blockchain.rhchain.blockchain.BlockchainAPI;
import com.sqli.blockchain.rhchain.login.LoginActivity;
import com.sqli.blockchain.rhchain.model.Question;
import com.sqli.blockchain.rhchain.server.QuestionsService;
import com.sqli.blockchain.rhchain.server.Server;
import com.sqli.blockchain.rhchain.server.UsersService;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.sqli.blockchain.rhchain.Constants.APP_ID;

/**
 * Created by gunicolas on 20/04/17.
 */

public class RHChainApplication extends EthereumApplication {

    public List<Question> questions;

    public BlockchainAPI blockchainAPI;
    public Server server;

    @Override
    public void onCreate() {
        super.onCreate();
        server = new Server();
    }

    @Override
    public void onEthereumServiceReady() {
        blockchainAPI = new BlockchainAPI(ethereumService.getIpcFilePath());

        server.questionsService.getQuestions()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(q -> Log.d(APP_ID,showQuestions(q)))
            .subscribe( questions -> RHChainApplication.this.questions = questions,
                        error -> Utils.showAlertDialog(RHChainApplication.this,error.getMessage()),
                        () -> startActivity(new Intent(this, LoginActivity.class)));

        super.onEthereumServiceReady();
    }

    String showQuestions(List<Question> questions){
        StringBuilder stringBuilder = new StringBuilder();
        for(Question q : questions){
            stringBuilder.append(q.toString());
        }
        return stringBuilder.toString();
    }

}
