package nantes_sqli.rhchain.blockchain;

import ethereumjava.solidity.ContractType;
import ethereumjava.solidity.element.function.SolidityFunction;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SBool;
import ethereumjava.solidity.types.SUInt;

public interface VotesContract extends ContractType {

    SolidityFunction<SBool> submit(@SArray.Size({3}) SArray<SUInt.SUInt8> votes);

    @SolidityFunction.ReturnParameters({@SArray.Size({3,3})})
    SolidityFunction<SArray<SArray<SUInt.SUInt8>>> getResults();
}
