package com.sqli.blockchain.rhchain;

import java.math.BigInteger;

/**
 * Created by gunicolas on 24/04/17.
 */

public abstract class Constants {

    public static final String APP_ID = "RHCHAIN";
    public static final String CONTRACT_ADDRESS = "0x165b58ca15a310bff38ef5402f6faee296282d4c";
    public static final BigInteger GAS = new BigInteger("300000");


    public static final String IP_ADMIN = "172.17.0.2";
    public static final String ENODE_ADMIN = "345e582054c5e379de058af8280076aa87ea82bf5dcc5de6c21d3b34e9773fb339eeeddeb3496d83b10dcd16ef1b5df5695fc2bcdedc1c3c375109743b2ad36a";
    public static final String ENODE_NODE2 = "0880b41c578384c5dbdf2cdca06cf77f7c16394d5955ecee67a5c2fb83e2966eb1bfbe48aa1fbc271dacda46c80ebed6365860c66d515fb999ab7484e8d8d29e";


    public static final String[] PEERS = new String[]{
            "enode://"+ENODE_ADMIN+"@"+IP_ADMIN+":30303",
            "enode://"+ENODE_NODE2+"@"+IP_ADMIN+":40303"
    };

}
