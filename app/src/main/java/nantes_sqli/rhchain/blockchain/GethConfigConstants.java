package nantes_sqli.rhchain.blockchain;

import java.math.BigInteger;

/**
 * Created by gunicolas on 16/01/17.
 */

public abstract class GethConfigConstants {


    public static final String APP_ID = "RHChain_APP";

    public static final String ACCOUNT_PASSWORD = "ETH_TEST";
    public static final String CONTRACT_ADDRESS = "0x165b58ca15a310bff38ef5402f6faee296282d4c";
    public static final BigInteger GAS = new BigInteger("300000");


    public static final String IP_ADMIN = "172.17.0.2";
    public static final String ENODE_ADMIN = "345e582054c5e379de058af8280076aa87ea82bf5dcc5de6c21d3b34e9773fb339eeeddeb3496d83b10dcd16ef1b5df5695fc2bcdedc1c3c375109743b2ad36a";
    public static final String ENODE_NODE2 = "fe5a32ef39e9fd2bc78e26a57a3e8ce356571b1765ce447aeb3a0181657b99dafe4a263ee7a58721b8488bd20980c794942ca5799324c6c59708dd4bdfbcce24";


    public static final String[] PEERS = new String[]{
        "enode://"+ENODE_ADMIN+"@"+IP_ADMIN+":30303",
        "enode://"+ENODE_NODE2+"@"+IP_ADMIN+":40303"
    };

}
