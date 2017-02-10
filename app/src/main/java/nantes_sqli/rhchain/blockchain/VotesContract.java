package nantes_sqli.rhchain.blockchain;

import ethereumjava.solidity.ContractType;
import ethereumjava.solidity.SolidityEvent;
import ethereumjava.solidity.SolidityFunction;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SBool;
import ethereumjava.solidity.types.SInt;
import ethereumjava.solidity.types.SUInt;
import ethereumjava.solidity.types.SVoid;

public interface VotesContract extends ContractType {

    @SolidityFunction.ReturnType(SBool.class)
    SolidityFunction<SBool> submit(@SArray.Type("uint8[3]") SArray<SUInt.SUInt8> votes);


//    @SolidityFunction.ReturnType(SUInt.SUInt256.class)
//    SolidityFunction<SUInt.SUInt256> getAlertRadius(SUInt.SUInt256 alertIdx);
//

}
