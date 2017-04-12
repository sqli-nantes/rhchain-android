package nantes_sqli.rhchain.blockchain;

import java.math.BigInteger;

/**
 * Created by gunicolas on 16/01/17.
 */

public abstract class GethConfigConstants {


    public static final String USERS_SERVER ="localhost:8000";
    public static final String APP_ID = "RHChain_APP";

    public static final String ACCOUNT_PASSWORD = "ETH_TEST";
    public static final String CONTRACT_ADDRESS = "0x8b2a6ed09f033f3c0bb9293570d7ea2aa0b3e07a";
    public static final BigInteger GAS = new BigInteger("300000");

    public static final String IP_ADMIN = "10.33.44.217";

    public static final String[] PEERS = new String[]{
        "enode://001eb425dd0c5b5ef60762d2b2c567268eb5d7ed573e4ddd6b036d2089a9412613fe3545a18bfc8d756e60e58caf16b673e1deb1f720d5958c67530e155580c1@172.17.0.2:30303"
    };

}
