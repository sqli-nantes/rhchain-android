package com.sqli.blockchain.rhchain.blockchain;

import android.util.Log;

import com.sqli.blockchain.rhchain.model.Results;

import org.ethereum.geth.Account;
import org.ethereum.geth.BigInt;
import org.ethereum.geth.Block;
import org.ethereum.geth.Geth;

import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;

import io.ethmobile.ethdroid.ChainConfig;
import io.ethmobile.ethdroid.EthDroid;
import io.ethmobile.ethdroid.KeyManager;
import io.ethmobile.ethdroid.Utils;
import io.ethmobile.ethdroid.exception.SmartContractException;
import io.ethmobile.ethdroid.solidity.element.returns.PairReturn;
import io.ethmobile.ethdroid.solidity.element.returns.SingleReturn;
import io.ethmobile.ethdroid.solidity.types.SArray;
import io.ethmobile.ethdroid.solidity.types.SBool;
import io.ethmobile.ethdroid.solidity.types.SInt;
import io.ethmobile.ethdroid.solidity.types.SType;
import io.ethmobile.ethdroid.solidity.types.SUInt;
import io.reactivex.Observable;

import static com.sqli.blockchain.rhchain.Constants.APP_ID;
import static com.sqli.blockchain.rhchain.Constants.GETH_VERBOSITY;
import static com.sqli.blockchain.rhchain.Constants.URL_BOOTNODE;
import static com.sqli.blockchain.rhchain.Constants.CONTRACT_ADDRESS;
import static com.sqli.blockchain.rhchain.Constants.GENESIS;
import static com.sqli.blockchain.rhchain.Constants.NETWORK_ID;

/**
 * Created by gunicolas on 20/04/17.
 */

public class BlockchainAPI {

    private static final int NOT_SUBMITTED_VALUE = -1;

    private VotesContract contract;
    private Account account;
    private EthDroid ethdroid;
    private KeyManager keyManager;

    public BlockchainAPI(String datadir) {

        //Utils.deleteDirIfExists(new File(datadir + "/GethDroid"));
        //Utils.deleteDirIfExists(new File(datadir + "/keystore"));

        createKeyManager(datadir);
        startEthdroid(datadir);
        initContract();
        this.account = getAccount();
    }

    private void createKeyManager(String datadir) {
        this.keyManager = KeyManager.newKeyManager(datadir);
    }

    private void startEthdroid(String datadir) {
        try {

            Geth.setVerbosity(GETH_VERBOSITY);

            ethdroid = new EthDroid.Builder(datadir)
                .withChainConfig(new ChainConfig.Builder(NETWORK_ID, GENESIS, URL_BOOTNODE).build())
                .withKeyManager(this.keyManager)
                .build();

            ethdroid.start();

            Log.d(APP_ID, "ETHDROID STARTED");

        } catch (Exception e) {
            Log.e(APP_ID, e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void initContract() {
        contract = ethdroid.getContractInstance(VotesContract.class, CONTRACT_ADDRESS);
        Log.d(APP_ID, "CONTRACT INITIALIZED");
    }

    private Account getAccount() {
        return ethdroid.getMainAccount();
    }

    public Observable<SingleReturn<SArray<SArray<SInt.SInt256>>>> registerPublishedEvent() {
        try {
            return contract.published().listen();
        } catch (Exception e) {
            Log.e(APP_ID, e.getLocalizedMessage());
        }
        return null;
    }

    public Observable registerClosedEvent() {
        try {
            return contract.closed().listen();
        } catch (Exception e) {
            Log.e(APP_ID, e.getLocalizedMessage());
        }
        return null;
    }

    public Observable registerOpenedEvent() {
        try {
            return contract.opened().listen();
        } catch (Exception e) {
            Log.e(APP_ID, e.getLocalizedMessage());
        }
        return null;
    }

    public boolean hasAccount() {
        return account != null;
    }

    public String createAccountAndUnlock(String password) {
        try {
            this.account = keyManager.newUnlockedAccount(password);
            ethdroid.setMainAccount(this.account);
        } catch (Exception e) {
            Log.e(APP_ID, e.getMessage());
        }
        Log.d(APP_ID, "ACCOUNT : " + this.account.getAddress().getHex());
        return this.account.getAddress().getHex();
    }

    public boolean unlockAccount(String password) {
        boolean unlocked;
        try {
            keyManager.unlockAccount(account, password);
            unlocked = true;
        } catch (Exception e) {
            unlocked = false;
            Log.e(APP_ID, e.getMessage());
        }
        Log.d(APP_ID, "UNLOCKED : " + unlocked);
        return unlocked;
    }

    public short getSurveyState() throws Exception {
        return contract.state().call().getElement1().get();
    }

    public boolean surveyExists() {
        return true;
    }

    /* get submitted survey if submitted, otherwise array filled with -1 if nothing submitted  */
    public int[] getSubmission(int nbQuestions) {
        int[] submissionRet = new int[nbQuestions];
        Arrays.fill(submissionRet, NOT_SUBMITTED_VALUE);

        try {
            PairReturn<SBool, SArray<SUInt.SUInt8>> submissionReturn = contract.mySubmission().call();

            boolean hasSubmitted = submissionReturn.getElement1().get();

            if (hasSubmitted) {
                SArray<SUInt.SUInt8> submission = submissionReturn.getElement2();

                SUInt.SUInt8[] submissionArray = submission.get();
                for (int i = 0; i < nbQuestions; i++) {
                    submissionRet[i] = submissionArray[i].get();
                }
            }

        } catch (SmartContractException ignored) {
            Log.e(APP_ID, ignored.getMessage());
        } catch (Exception e) {
            Log.e(APP_ID, e.getMessage());
        }
        return submissionRet;
    }

    public boolean canSubmit(int[] submission) {
        return submission != null && submission.length >= 1 && submission[0] == NOT_SUBMITTED_VALUE;
    }

    public Observable<Block> submit(int[] submission) throws Exception {

        int submissionLength = submission.length;
        SUInt.SUInt8[] suint8Array = new SUInt.SUInt8[submission.length];

        for (int i = 0; i < submissionLength; i++) {
            suint8Array[i] = SUInt.SUInt8.fromShort((short) submission[i]);
        }

        return contract.submit(SArray.fromArray(suint8Array))
            .getTransaction()
            .gasAmount(new BigInt(400000))
            .sendWithNotification();
    }

    public Results getResults(int nbQuestions, int nbAnswers) {

        try {
            PairReturn<SBool, SArray<SArray<SInt.SInt256>>> response = contract.getResults().call();

            SArray<SArray<SInt.SInt256>> results = response.getElement2();

            SType[] questionArray = results.get();
            int[][] ret = new int[nbQuestions][nbAnswers];

            for (int i = 0; i < nbQuestions; i++) {
                SType[] questionAnswers = ((SArray<SInt.SInt256>) questionArray[i]).get();
                for (int j = 0; j < nbAnswers; j++) {
                    int questionAnswerResult = ((BigInteger) questionAnswers[j].get()).intValue();
                    ret[i][j] = questionAnswerResult;
                }
            }
            return new Results(ret);
        } catch (Exception e) {
            Log.e(APP_ID, e.getMessage());
            return null;
        }
    }

    public boolean hasMoney() {
        try {
            return ethdroid.getBalanceOf(account).inWei() > 0;
        } catch (Exception e) {
            Log.e(APP_ID, e.getMessage());
            return false;
        }
    }

}
