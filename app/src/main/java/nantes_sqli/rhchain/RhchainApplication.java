package nantes_sqli.rhchain;

import android.util.Log;

import com.sqli.blockchain.android_geth.EthereumApplication;

import nantes_sqli.rhchain.blockchain.GethManager;


public class RhchainApplication extends EthereumApplication {
    public GethManager gethManager;

    @Override
    public void onEthereumServiceReady() {
        gethManager = new GethManager(ethereumService.getIpcFilePath());

        super.onEthereumServiceReady();

        Log.d("app","onEtherum service Ready");
        super.onEthereumServiceReady();

    }
}
