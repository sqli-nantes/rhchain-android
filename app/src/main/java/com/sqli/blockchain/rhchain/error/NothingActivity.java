package com.sqli.blockchain.rhchain.error;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.RHChainAbstractActivity;

/**
 * Created by gunicolas on 19/04/17.
 */

public class NothingActivity extends RHChainAbstractActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nothing_activity);
    }
}
