package com.sqli.blockchain.rhchain.error;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.RHChainAbstractActivity;
import com.sqli.blockchain.rhchain.survey.SurveyActivity;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by gunicolas on 19/04/17.
 */

public class NothingActivity extends RHChainAbstractActivity {


    Subscription openedSubscription;

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
        openedSubscription.unsubscribe();
        super.onStop();
    }
}
