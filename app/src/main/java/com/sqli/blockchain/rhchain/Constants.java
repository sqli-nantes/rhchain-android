package com.sqli.blockchain.rhchain;

import java.math.BigInteger;

/**
 * Created by gunicolas on 24/04/17.
 */

public abstract class Constants {

    public static final int SATISFIED_ID = 0;
    public static final int NEUTRAL_ID = 1;
    public static final int UNSATISFIED_ID = 2;


    public static final String APP_ID = "RHCHAIN";
    public static final String CONTRACT_ADDRESS = "0x165b58ca15a310bff38ef5402f6faee296282d4c";
    public static final BigInteger GAS = new BigInteger("300000");


    public static final String IP_ADMIN = "10.33.44.111";
    public static final String ENODE_ADMIN = "345e582054c5e379de058af8280076aa87ea82bf5dcc5de6c21d3b34e9773fb339eeeddeb3496d83b10dcd16ef1b5df5695fc2bcdedc1c3c375109743b2ad36a";
    public static final String ENODE_NODE2 = "80ee06719c4f4396c46a65099edd466d03a89b2531f2c7df9a12e0eeafd32e1989456b8f2e5cb9a364ae88c7631bf93948703f813a5430542e83e303bae38205";


    public static final String[] PEERS = new String[]{
            "enode://"+ENODE_ADMIN+"@"+IP_ADMIN+":30303",
            "enode://"+ENODE_NODE2+"@"+IP_ADMIN+":40303"
    };

}
