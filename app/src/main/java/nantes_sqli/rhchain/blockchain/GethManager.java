package nantes_sqli.rhchain.blockchain;

import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ethereumjava.EthereumJava;
import ethereumjava.exception.EthereumJavaException;
import ethereumjava.module.objects.Transaction;
import ethereumjava.net.provider.AndroidIpcProvider;
import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SUInt;
import nantes_sqli.rhchain.data.Answer;
import nantes_sqli.rhchain.data.QuestionResultat;
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
            final BigInteger balance = eth.eth.balance("0x3172dfb9d63d3e150e1d00a0dfe895c439cb897c", null);

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


    /**
     * Permet la transmission des reponses saisies au vote
     * @param reponses la liste des reponses soumises
     * @return Observable<Transaction>
     */
    public Observable<Transaction> sendVotes(Results reponses) {

        //reponses.getSurvey().getContratAdresse
        final Answer[] reponsesSelectionnees = reponses.getReponseSelectionnees();
        VotesContract contract = ethereumJava.contract.withAbi(VotesContract.class).at(CONTRACT_ADDRESS);
        SUInt.SUInt8[] votesSolidity = new SUInt.SUInt8[reponsesSelectionnees.length];

        for (int i = 0; i < reponsesSelectionnees.length; i++) {
            votesSolidity[i] = SUInt.SUInt8.fromShort((short) reponsesSelectionnees[i].getValue());
        }

        String from = getDefaultAccount();


        Observable<Transaction> transactionObservable = contract.submit(SArray.fromArray(votesSolidity)).sendTransactionAndGetMined(from, GAS);

        return transactionObservable;
    }

    public ArrayList<QuestionResultat> getResults(Survey survey, Results reponses) {

        //Retrieve the smart contract interface
        VotesContract contract = ethereumJava.contract.withAbi(VotesContract.class).at(CONTRACT_ADDRESS);

        //mapping results
        ArrayList<QuestionResultat> questionResultats = new ArrayList<>();

        //Calling the service for retrieve results of the survey
        SArray<SArray<SUInt.SUInt8>> results = contract.getResults().call();
        for (int i = 0; i < results.get().length; i++) {
            //retrieve question in original survey
            final String question = survey.getQuestion(i).getTextQuestion();

            // Creating instance of results
            ArrayList<Integer> resultats = new ArrayList<Integer>();

            // Mapping resultat to integer
            SUInt.SUInt8[] resultstArray = results.get()[i].get();
            for (int j = 0; j < resultstArray.length; j++) {
                resultats.add(Integer.valueOf(resultstArray[j].get()));
            }
            questionResultats.add(new QuestionResultat(question, (Integer[]) resultats.toArray()));

        }
        return questionResultats;
    }


}
