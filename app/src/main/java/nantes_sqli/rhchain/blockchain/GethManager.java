package nantes_sqli.rhchain.blockchain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ethereumjava.EthereumJava;
import ethereumjava.exception.EthereumJavaException;
import ethereumjava.module.objects.Transaction;
import ethereumjava.net.provider.AndroidIpcProvider;
import ethereumjava.solidity.element.returns.PairReturn;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SBool;
import ethereumjava.solidity.types.SInt.SInt256;
import ethereumjava.solidity.types.SType;
import ethereumjava.solidity.types.SUInt;
import nantes_sqli.rhchain.data.Answer;
import nantes_sqli.rhchain.data.Results;
import nantes_sqli.rhchain.data.Survey;
import rx.Observable;
import rx.Subscription;

import static nantes_sqli.rhchain.blockchain.GethConfigConstants.ACCOUNT_PASSWORD;
import static nantes_sqli.rhchain.blockchain.GethConfigConstants.APP_ID;
import static nantes_sqli.rhchain.blockchain.GethConfigConstants.CONTRACT_ADDRESS;
import static nantes_sqli.rhchain.blockchain.GethConfigConstants.GAS;
import static nantes_sqli.rhchain.blockchain.GethConfigConstants.PEERS;

/**
 * Created by gunicolas on 21/01/17.
 */

public class GethManager {


    private EthereumJava ethereumJava;

    private String defaultAccount;

    private Subscription alertSubscription;

    public GethManager(String ipcFilePath) {

        ethereumJava = new EthereumJava.Builder()
            .provider(new AndroidIpcProvider(ipcFilePath))
            .build();

        Log.d(APP_ID, "ETHEREUM-JAVA STARTED");

        addPeers();
        Log.d(APP_ID, "PEERS ADDED");
    }


    public void addPeers() {
        for (String peer : PEERS) {
            ethereumJava.admin.addPeer(peer);
        }
    }

    public String createAccountIfNeeded() throws RuntimeException {
        List<String> accounts = ethereumJava.personal.listAccounts();
        if (accounts.size() == 0) {
            return ethereumJava.personal.newAccount(ACCOUNT_PASSWORD);
        } else {
            return getDefaultAccount();
        }
    }

    /**
     * Permet de creer le compte utilisateur
     *
     * @param password
     * @return
     * @throws RuntimeException
     */
    public String createDefaultAccount(String password) throws RuntimeException {
        defaultAccount = ethereumJava.personal.newAccount(password);
        Log.d(APP_ID, "Account Created " + defaultAccount);
        return defaultAccount;
    }

    /**
     * Permet de récuperer le compte utilisateur
     *
     * @return
     * @throws RuntimeException
     */
    public String getDefaultAccount() throws RuntimeException {
        List<String> accounts = ethereumJava.personal.listAccounts();
        if (accounts == null || accounts.size() == 0) {
            throw new RuntimeException("no default account");
        } else {
            return accounts.get(0);
        }
    }

    /**
     * Permet de débloquer le compte utilisateur par default avec le mot de passe passé en paramètre
     *
     * @param password le mot de passe du compte par default
     * @return
     * @throws RuntimeException
     */
    public boolean unlockDefaultAccountSession(String password) throws RuntimeException {

        EthereumJava eth = getEthereumJava();
        boolean isSucess = false;
        try {
            isSucess = eth.personal.unlockAccount(getDefaultAccount(), password, 0);
        } catch (EthereumJavaException e) {
            isSucess = false;
        }

        return isSucess;
    }

    public String getNodeId() {
        return ethereumJava.admin.nodeInfo().enode;
    }

    public EthereumJava getEthereumJava() {
        return ethereumJava;
    }

    public Observable<BigInteger> currentBalance() {
        String from = getDefaultAccount();
        final Observable<BigInteger> observable = ethereumJava.eth.getBalance(from, null);
        return observable;
    }

    /**
     * Permet la transmission des reponses saisies au vote
     * @param reponses la liste des reponses soumises
     * @return Observable<Transaction>
     */
    public Observable<Transaction> sendVotes(Results reponses) {

        final Answer[] reponsesSelectionnees = reponses.getReponseSelectionnees();
        VotesContract contract = ethereumJava.contract.withAbi(VotesContract.class).at(CONTRACT_ADDRESS);
        SUInt.SUInt8[] votesSolidity = new SUInt.SUInt8[reponsesSelectionnees.length];

        for (int i = 0; i < reponsesSelectionnees.length; i++) {
            votesSolidity[i] = SUInt.SUInt8.fromShort((short) reponsesSelectionnees[i].getValue());
        }

        String from = getDefaultAccount();

        BigInteger solde =  ethereumJava.eth.balance(from,null);
        Log.d(from, "solde avant Soumission : " + solde.toString());

        Observable<Transaction> transactionObservable = contract.submit(SArray.fromArray(votesSolidity)).sendTransactionAndGetMined(from, GAS);
        return transactionObservable;

    }

    public ArrayList<Integer[]> getResults(@Nullable  Survey survey) {

        //Retrieve the smart contract interface
        VotesContract contract = ethereumJava.contract.withAbi(VotesContract.class).at(CONTRACT_ADDRESS);

        //Calling the service for retrieve results of the survey
        final PairReturn<SBool, SArray<SArray<SInt256>>> singleReturn = contract.getResults().call();
        final SBool bool = singleReturn.getElement1();
        final SArray<SArray<SInt256>> resultmatrix = singleReturn.getElement2();


        //mapping results POur chaque questions
        ArrayList<Integer[]> questionResultats = new ArrayList<>();

        final SType[] resultsType = resultmatrix.get();
        for (int i = 0; i < resultsType.length; i++) {
            final SArray<SInt256> resultsLine = (SArray<SInt256>) resultsType[i];
            questionResultats.add(mappingResultsLine(resultsLine));

        }
        return questionResultats;
    }

    @NonNull
    private Integer[] mappingResultsLine(SArray<SInt256> resultsLine) {
        ArrayList<Integer> resultats = new ArrayList<>();
        if(resultsLine != null ) {

            final SType[] resultsType = resultsLine.get();
            for (int j = 0; j < resultsType.length; j++) {
                SInt256 resultValue = (SInt256) resultsType[j];
                BigInteger bigintValue = (BigInteger) resultValue.get();
                resultats.add(Integer.valueOf(bigintValue.intValue()));
            }
        }
        return resultats.toArray(new Integer[resultats.size()]);

    }


}
