package com.sqli.blockchain.rhchain;

/**
 * Created by gunicolas on 24/04/17.
 */

public abstract class Constants {

    public static final int SATISFIED_ID = 0;
    public static final int NEUTRAL_ID = 1;
    public static final int UNSATISFIED_ID = 2;

    public static final short SURVEY_OPENED = 0;
    public static final short SURVEY_PUBLISHED = 1;
    public static final short SURVEY_CLOSED = 2;

    public static final long GETH_VERBOSITY = 3;

    public static final String APP_ID = "RHCHAIN";
    public static final String CONTRACT_ADDRESS = "0x5ee1d58e02d2176e426098600db25198a06d342e";

    public static final String IP_BOOTNODE = "10.33.44.181";
    public static final String PORT_BOOTNODE = "30301";
    public static final String DISC_PORT_BOOTNODE = "30302";
    public static final String ENODE_BOOTNODE = "887a2e2c1e08426aed91ceead524b8110ded7abf6f76cca1b49a752e379d41ce3fc309ac784bd41655e758c87205a4c70d9ecad1072f1f8431251e821d75599b";
    public static final String URL_BOOTNODE = "enode://" + ENODE_BOOTNODE + "@" + IP_BOOTNODE + ":" + PORT_BOOTNODE + "?discport:" + DISC_PORT_BOOTNODE;

    public static final String IP_ADMIN = IP_BOOTNODE;
    public static final String PORT_ADMIN = "8000";

    public static final String GENESIS = "{\"config\": {\"chainId\": 100, \"homesteadBlock\": 0, \"eip155Block\": 0, \"eip158Block\": 0 }, \"nonce\": \"0x0000000000000042\", \"timestamp\": \"0x0\", \"parentHash\": \"0x0000000000000000000000000000000000000000000000000000000000000000\", \"extraData\": \"\", \"gasLimit\": \"0x8000000\", \"difficulty\": \"0x100\", \"mixhash\": \"0x0000000000000000000000000000000000000000000000000000000000000000\", \"coinbase\": \"0x0000000000000000000000000000000000000042\", \"alloc\": {} }";
    public static final long NETWORK_ID = 100;


}
