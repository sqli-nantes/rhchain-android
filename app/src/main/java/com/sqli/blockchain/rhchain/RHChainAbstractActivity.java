package com.sqli.blockchain.rhchain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sqli.blockchain.android_geth.EthereumService;

/**
 * Created by gunicolas on 20/04/17.
 */

public abstract class RHChainAbstractActivity extends AppCompatActivity  {


    protected RHChainApplication application;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (RHChainApplication) getApplication();
    }
}
