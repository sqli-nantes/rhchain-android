package nantes_sqli.rhchain.blockchain;

import ethereumjava.solidity.ContractType;
import ethereumjava.solidity.SolidityEvent;
import ethereumjava.solidity.SolidityFunction;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SInt;
import ethereumjava.solidity.types.SUInt;
import ethereumjava.solidity.types.SVoid;

public interface VotesContract extends ContractType {

    @SolidityFunction.ReturnType(SVoid.class)
    SolidityFunction<SVoid> sendVotes(SArray<SUInt.SUInt256> votes);


    @SolidityFunction.ReturnType(SVoid.class)
    SolidityFunction<SVoid> signalSafety(SUInt.SUInt256 alertIdx);

//    @SolidityFunction.ReturnType(SUInt.SUInt256.class)
//    SolidityFunction<SUInt.SUInt256> getAlertRadius(SUInt.SUInt256 alertIdx);
//

}
