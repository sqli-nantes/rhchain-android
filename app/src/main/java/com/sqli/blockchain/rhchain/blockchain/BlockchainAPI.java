package com.sqli.blockchain.rhchain.blockchain;

import android.util.Log;

import com.sqli.blockchain.rhchain.model.Results;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ethereumjava.EthereumJava;
import ethereumjava.exception.EthereumJavaException;
import ethereumjava.exception.SmartContractException;
import ethereumjava.module.objects.Block;
import ethereumjava.module.objects.Transaction;
import ethereumjava.net.provider.AndroidIpcProvider;
import ethereumjava.solidity.element.returns.PairReturn;
import ethereumjava.solidity.element.returns.SingleReturn;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SBool;
import ethereumjava.solidity.types.SInt;
import ethereumjava.solidity.types.SType;
import ethereumjava.solidity.types.SUInt;
import rx.Observable;

import static com.sqli.blockchain.rhchain.Constants.APP_ID;
import static com.sqli.blockchain.rhchain.Constants.CONTRACT_ADDRESS;
import static com.sqli.blockchain.rhchain.Constants.GAS;
import static com.sqli.blockchain.rhchain.Constants.PEERS;

/**
 * Created by gunicolas on 20/04/17.
 */

public class BlockchainAPI {

    private static final int NOT_SUBMITTED_VALUE = -1;

    private EthereumJava ethereumJava;
    private VotesContract contract;
    private String accountId;


    public BlockchainAPI(String ipcFilePath) {

        startEthereumJava(ipcFilePath);
        addPeers();
        initContract();

        this.accountId = getAccount();

    }

    public Observable<SingleReturn<SArray<SArray<SInt.SInt256>>>> registerOverEvent() {
        return contract.over().watch();
    }

    private void startEthereumJava(String ipcFilePath){
        ethereumJava = new EthereumJava.Builder()
                .provider(new AndroidIpcProvider(ipcFilePath))
                .build();
        Log.d(APP_ID, "ETHEREUM-JAVA STARTED");
    }
    private void addPeers() {
        for (String peer : PEERS) {
            ethereumJava.admin.addPeer(peer);
        }
        Log.d(APP_ID, "PEERS ADDED");
    }
    private void initContract(){
        contract = ethereumJava.contract.withAbi(VotesContract.class).at(CONTRACT_ADDRESS);
        Log.d(APP_ID, "CONTRACT INITIALIZED");
    }


    public boolean hasAccount(){
        return accountId != null;
    }
    private String getAccount(){
        List<String> accounts = ethereumJava.personal.listAccounts();
        return accounts.size() > 0 ? accounts.get(0) : null;
    }
    public String createAccountAndUnlock(String password){
        this.accountId = ethereumJava.personal.newAccount(password);
        ethereumJava.personal.unlockAccount(this.accountId,password,0);
        Log.d(APP_ID,"ACCOUNT : "+this.accountId);
        return this.accountId;
    }
    public boolean unlockAccount(String password){
        boolean unlocked;
        try{
            unlocked = ethereumJava.personal.unlockAccount(this.accountId,password,0);
        } catch(EthereumJavaException e){
            unlocked = false;
        }
        Log.d(APP_ID,"UNLOCKED : "+unlocked);
        return unlocked;
    }

    public boolean isSurveyOver(){
        return contract.closed().call().getElement1().get();
    }
    public boolean surveyExists(){
        return true;
    }

    /* get submitted survey if submitted, otherwise array filled with -1 if nothing submitted  */
    public int[] getSubmission(int nbQuestions){
        int[] submissionRet = new int[nbQuestions];
        Arrays.fill(submissionRet,NOT_SUBMITTED_VALUE);

        try {
            PairReturn<SBool, SArray<SUInt.SUInt8>> submissionReturn = contract.mySubmission().call();

            boolean hasSubmitted = submissionReturn.getElement1().get();

            if( hasSubmitted ) {
                SArray<SUInt.SUInt8> submission = submissionReturn.getElement2();

                SUInt.SUInt8[] submissionArray = submission.get();
                for (int i = 0; i < nbQuestions; i++) {
                    submissionRet[i] = submissionArray[i].get();
                }
            }

        } catch(SmartContractException ignored){}
        return submissionRet;
    }
    public boolean canSubmit(int[] submission) {
        return submission != null && submission.length >= 1 && submission[0] == NOT_SUBMITTED_VALUE;
    }
    public Observable<Transaction> submit(int[] submission){

        int submissionLength = submission.length;
        SUInt.SUInt8[] suint8Array = new SUInt.SUInt8[submission.length];

        for(int i=0;i<submissionLength;i++){
            suint8Array[i] = SUInt.SUInt8.fromShort((short) submission[i]);
        }

        return contract.submit(SArray.fromArray(suint8Array)).sendTransactionAndGetMined(accountId,GAS);
    }

    public Results getResults(int nbQuestions,int nbAnswers){

        PairReturn<SBool, SArray<SArray<SInt.SInt256>>> response = contract.getResults().call();
        SArray<SArray<SInt.SInt256>> results = response.getElement2();

        SType[] questionArray = results.get();
        int[][] ret = new int[nbQuestions][nbAnswers];

        for(int i=0;i<nbQuestions;i++){
            SType[] questionAnswers = ((SArray<SInt.SInt256>)questionArray[i]).get();
            for(int j=0;j<nbAnswers;j++){
                int questionAnswerResult = ((BigInteger)questionAnswers[j].get()).intValue();
                ret[i][j] = questionAnswerResult;
            }
        }
        return new Results(ret);
    }

    public boolean hasMoney(){
        return ethereumJava.eth.balance(accountId,Block.BlockParameter.LATEST).compareTo(BigInteger.ZERO) > 0;
    }

}
