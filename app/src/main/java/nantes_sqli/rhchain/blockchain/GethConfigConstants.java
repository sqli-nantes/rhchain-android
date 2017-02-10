package nantes_sqli.rhchain.blockchain;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * Created by gunicolas on 16/01/17.
 */

public abstract class GethConfigConstants {


    public static final String APP_ID = "RHChain_APP";

    public static final String ACCOUNT_PASSWORD = "ETH_TEST";
    public static final String CONTRACT_ADDRESS_TEST = "0x4ee6648f4154b818bd013d4ab9ea887fe9e5fa57";
    public static final BigInteger GAS = new BigInteger("300000");

    public static final String[] PEERS = new String[]{
        "enode://9c59616498d7be4823aed4145496d2bc4a8426263cc0b65cbb278d5637f5c5420dfcb4e5d947b6a16f0c1df3dea3d92cc477a25e7239beda5585fb52d987df14@10.33.44.57:30303"

    };

}
