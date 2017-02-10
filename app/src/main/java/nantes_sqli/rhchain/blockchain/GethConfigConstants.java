package nantes_sqli.rhchain.blockchain;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * Created by gunicolas on 16/01/17.
 */

public abstract class GethConfigConstants {


    public static final String APP_ID = "RHChain_APP";

    public static final String ACCOUNT_PASSWORD = "ETH_TEST";
    public static final String CONTRACT_ADDRESS = "0xa1fa141192e14bbc87112b51e20438040d4bba74";
    public static final BigInteger GAS = new BigInteger("300000");

    public static final String[] PEERS = new String[]{
        "enode://9c59616498d7be4823aed4145496d2bc4a8426263cc0b65cbb278d5637f5c5420dfcb4e5d947b6a16f0c1df3dea3d92cc477a25e7239beda5585fb52d987df14@10.33.44.57:30303",
        "enode://df6b0ab0cba157d29fd12acd88face40714236af015d44030d6a44233d10e878b27553786c61d40407e3b329fc3bfb6f01f36b4be4b8ce6cdc7283cf420adb0a@10.33.44.57:30304"
    };

}
