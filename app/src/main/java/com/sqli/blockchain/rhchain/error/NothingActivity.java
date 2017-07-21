package com.sqli.blockchain.rhchain.error;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.RHChainAbstractActivity;
import com.sqli.blockchain.rhchain.survey.SurveyActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by gunicolas on 19/04/17.
 */

public class NothingActivity extends RHChainAbstractActivity {


    Disposable openedSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nothing_activity);


        openedSubscription = application.blockchainAPI.registerOpenedEvent()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(o -> showSurveyActivity());

    }

    private void showSurveyActivity() {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStop() {
        openedSubscription.dispose();
        super.onStop();
    }
}
