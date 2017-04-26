package com.sqli.blockchain.rhchain.blockchain;

import ethereumjava.solidity.ContractType;
import ethereumjava.solidity.element.SolidityElement;
import ethereumjava.solidity.element.event.SolidityEvent;
import ethereumjava.solidity.element.event.SolidityEvent2;
import ethereumjava.solidity.element.function.SolidityFunction;
import ethereumjava.solidity.element.function.SolidityFunction2;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SBool;
import ethereumjava.solidity.types.SInt;
import ethereumjava.solidity.types.SUInt;

public interface VotesContract extends ContractType {


    @SolidityElement.ReturnParameters(@SArray.Size({3,3}))
    SolidityEvent<SArray<SArray<SInt.SInt256>>> over();

    SolidityFunction<SBool> closed();

    SolidityFunction<SBool> submit(@SArray.Size({3}) SArray<SUInt.SUInt8> votes);

    @SolidityFunction.ReturnParameters({@SArray.Size(), @SArray.Size({3,3})})
    SolidityFunction2<SBool, SArray<SArray<SInt.SInt256>>> getResults();

    @SolidityFunction.ReturnParameters({@SArray.Size(), @SArray.Size({3})})
    SolidityFunction2<SBool,SArray<SUInt.SUInt8>> mySubmission();
}
