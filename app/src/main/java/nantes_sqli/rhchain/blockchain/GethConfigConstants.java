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
        "enode://1dcd97293b36e31afff9a42f81b88ad85dd3f74d63592116c8b74ceb51d9dbe077b4cb509e38ede1b8bb0c4353ad314d259a016a0b900b112f05690a9548d187@10.0.2.2:30303"

    };

}
