package nantes_sqli.rhchain.blockchain;

import ethereumjava.solidity.ContractType;
import ethereumjava.solidity.SolidityFunction;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SBool;
import ethereumjava.solidity.types.SUInt;
import ethereumjava.solidity.types.SVoid;

public interface VotesContract extends ContractType {

    @SolidityFunction.ReturnType(SVoid.class)
    SolidityFunction<SVoid> sendVotes(SArray<SUInt.SUInt256> votes);


    @SolidityFunction.ReturnType(SBool.class)
    SolidityFunction<SBool> submit(@SArray.Type("uint8[3]") SArray<SUInt.SUInt8> votes);

    @SolidityFunction.ReturnType(SArray.class)
    SolidityFunction<SArray<SArray<SUInt.SUInt8>>> getResults();
}
