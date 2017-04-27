package com.sqli.blockchain.rhchain.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.RHChainAbstractActivity;

/**
 * Created by gunicolas on 26/04/17.
 */

public class Splashscreen extends RHChainAbstractActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);
    }
}
