package com.sqli.blockchain.rhchain.blockchain;

import io.ethmobile.ethdroid.solidity.ContractType;
import io.ethmobile.ethdroid.solidity.element.SolidityElement;
import io.ethmobile.ethdroid.solidity.element.event.SolidityEvent;
import io.ethmobile.ethdroid.solidity.element.function.SolidityFunction;
import io.ethmobile.ethdroid.solidity.element.function.SolidityFunction2;
import io.ethmobile.ethdroid.solidity.types.SArray;
import io.ethmobile.ethdroid.solidity.types.SBool;
import io.ethmobile.ethdroid.solidity.types.SInt;
import io.ethmobile.ethdroid.solidity.types.SUInt;

public interface VotesContract extends ContractType {


    @SolidityElement.ReturnParameters(@SArray.Size({3,3}))
    SolidityEvent<SArray<SArray<SInt.SInt256>>> published();

    SolidityEvent closed();
    SolidityEvent opened();

    SolidityFunction<SUInt.SUInt8> state();

    SolidityFunction<SBool> submit(@SArray.Size({3}) SArray<SUInt.SUInt8> votes);

    @SolidityFunction.ReturnParameters({@SArray.Size(), @SArray.Size({3,3})})
    SolidityFunction2<SBool, SArray<SArray<SInt.SInt256>>> getResults();

    @SolidityFunction.ReturnParameters({@SArray.Size(), @SArray.Size({3})})
    SolidityFunction2<SBool,SArray<SUInt.SUInt8>> mySubmission();
}
