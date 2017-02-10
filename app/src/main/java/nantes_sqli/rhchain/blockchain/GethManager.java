package nantes_sqli.rhchain.blockchain;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ethereumjava.EthereumJava;
import ethereumjava.exception.EthereumJavaException;
import ethereumjava.module.objects.Block;
import ethereumjava.module.objects.Peer;
import ethereumjava.net.provider.AndroidIpcProvider;
import rx.Subscription;


import static nantes_sqli.rhchain.blockchain.GethConfigConstants.ACCOUNT_PASSWORD;
import static nantes_sqli.rhchain.blockchain.GethConfigConstants.APP_ID;
import static nantes_sqli.rhchain.blockchain.GethConfigConstants.PEERS;

/**
 * Created by gunicolas on 21/01/17.
 */

public class GethManager {


    private EthereumJava ethereumJava;

    private String defaultAccount;

    private Subscription alertSubscription;

    public GethManager(String ipcFilePath){

        ethereumJava = new EthereumJava.Builder()
                .provider(new AndroidIpcProvider(ipcFilePath))
                .build();

        Log.d(APP_ID,"ETHEREUM-JAVA STARTED");

        addPeers();
        Log.d(APP_ID,"PEERS ADDED");
    }



    public void addPeers(){
        for(String peer : PEERS){
            ethereumJava.admin.addPeer(peer);
        }
    }

    public String createAccountIfNeeded() throws RuntimeException {
        List<String> accounts = ethereumJava.personal.listAccounts();
        if( accounts.size() == 0 ){
            return ethereumJava.personal.newAccount(ACCOUNT_PASSWORD);
        } else{
            return getDefaultAccount();
        }
    }

    /**
     * Permet de creer le compte utilisateur
     * @param password
     * @return
     * @throws RuntimeException
     */
    public String createDefaultAccount(String password) throws RuntimeException {
        defaultAccount = ethereumJava.personal.newAccount(password);
        Log.d(APP_ID,"Account Created "+defaultAccount);
        return defaultAccount;
    }

    /**
     * Permet de récuperer le compte utilisateur
     * @return
     * @throws RuntimeException
     */
    public String getDefaultAccount() throws RuntimeException {
        List<String> accounts = ethereumJava.personal.listAccounts();
        if( accounts == null || accounts.size() == 0 ){
            throw new RuntimeException("no default account");
        } else{
            return accounts.get(0);
        }
    }

    /**
     * Permet de débloquer le compte utilisateur par default avec le mot de passe passé en paramètre
     * @param password le mot de passe du compte par default
     * @return
     * @throws RuntimeException
     */
    public boolean unlockDefaultAccountSession(String password) throws RuntimeException  {

        EthereumJava eth = getEthereumJava();
        boolean isSucess = false;
        try {
            isSucess = eth.personal.unlockAccount(getDefaultAccount(), password, 0);

        }catch (EthereumJavaException e){
            isSucess = false;
        }

        return isSucess;
    }

    public String getNodeId(){
        return ethereumJava.admin.nodeInfo().enode;
    }

    public EthereumJava getEthereumJava() {
        return ethereumJava;
    }


    public boolean sendVotes(int[] reponsesVote){


        return true;
    }

}
