package com.sqli.blockchain.rhchain.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sqli.blockchain.rhchain.Utils;
import com.sqli.blockchain.rhchain.error.NothingActivity;
import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.RHChainAbstractActivity;
import com.sqli.blockchain.rhchain.model.User;
import com.sqli.blockchain.rhchain.results.ResultActivity;
import com.sqli.blockchain.rhchain.survey.SurveyActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.sqli.blockchain.rhchain.Constants.APP_ID;
import static com.sqli.blockchain.rhchain.Constants.SURVEY_CLOSED;
import static com.sqli.blockchain.rhchain.Constants.SURVEY_OPENED;
import static com.sqli.blockchain.rhchain.Constants.SURVEY_PUBLISHED;

public class LoginActivity extends RHChainAbstractActivity implements View.OnClickListener {

    TextView loginAddressTextView;
    EditText loginAddressEdittext;
    EditText loginPasswordEdittext;
    EditText loginConfirmPasswordEdittext;
    Button loginButton;

    ProgressBar loginProgressbar;
    View rootView;
    View mainview;

    boolean loginMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginAddressTextView = (TextView) findViewById(R.id.login_address_textview);
        loginAddressEdittext = (EditText) findViewById(R.id.login_address_edittext);
        loginPasswordEdittext = (EditText) findViewById(R.id.login_password_edittext);
        loginConfirmPasswordEdittext = (EditText) findViewById(R.id.login_confirm_password_edittext);
        loginButton = (Button) findViewById(R.id.login_button);

        loginProgressbar = (ProgressBar) findViewById(R.id.login_progressbar);
        rootView = findViewById(R.id.login_root_layout);
        mainview = findViewById(R.id.login_main_layout);

        loginButton.setOnClickListener(this);

        loginMode = application.blockchainAPI.hasAccount();

        setLoadingView(false);
        updateViewState(loginMode);

    }

    boolean updateViewState(boolean loginMode) {
        loginAddressTextView.setVisibility(loginMode ? View.VISIBLE : View.GONE);
        loginAddressEdittext.setVisibility(loginMode ? View.GONE : View.VISIBLE);
        loginConfirmPasswordEdittext.setVisibility(loginMode ? View.GONE : View.VISIBLE);
        return true;
    }

    boolean setLoadingView(boolean loading) {
        mainview.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
        loginProgressbar.setVisibility(loading ? View.VISIBLE : View.INVISIBLE);
        return true;
    }

    void nextActivity() {

        Class<? extends RHChainAbstractActivity> nextActivity;

        short state = -1000;
        try {
            state = application.blockchainAPI.getSurveyState();
        } catch (Exception e) {
            Log.e(APP_ID, e.getMessage());

        }
        switch (state) {
            case SURVEY_OPENED:
                nextActivity = SurveyActivity.class;
                break;
            case SURVEY_PUBLISHED:
                nextActivity = ResultActivity.class;
                break;
            case SURVEY_CLOSED:
                nextActivity = NothingActivity.class;
                break;
            default:
                nextActivity = NothingActivity.class;
                Log.d(APP_ID, "error in switch case loginActivity - default : " + state);
                break;
        }

        Intent intent = new Intent(this, nextActivity);
        startActivity(intent);
    }

    void manageAccount() {

        String password = loginPasswordEdittext.getText().toString();
        boolean validPassword = password.length() > 0;


        if (!loginMode) {


            String passwordConfirm = loginConfirmPasswordEdittext.getText().toString();
            String emailAddress = loginAddressEdittext.getText().toString();

            boolean validEmail = Utils.isAddressValid(emailAddress);

            validPassword = validPassword
                && passwordConfirm.length() > 0
                && password.equals(passwordConfirm);

            String emailValidityText = !validEmail ? "Format email invalide" : null;
            loginAddressEdittext.setError(emailValidityText);

            String passwordValidityText = !validPassword ? "Erreur mot de passe" : null;
            loginPasswordEdittext.setError(passwordValidityText);
            loginConfirmPasswordEdittext.setError(passwordValidityText);

            if (validEmail && validPassword) {

                Observable.just(true)
                    .map(v -> setLoadingView(true))
                    .observeOn(Schedulers.newThread())
                    .map(v -> application.blockchainAPI.createAccountAndUnlock(password))
                    .map(address -> new User(emailAddress, address))
                    .flatMap(user -> application.server.registrationService.createUser(user))
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(user -> setLoadingView(false))
                    .subscribe(ok -> nextActivity(),
                        error -> {
                            Utils.showAlertDialog(LoginActivity.this, error.getMessage());
                            setLoadingView(false);
                        });
            }
        } else {
            boolean unlocked = application.blockchainAPI.unlockAccount(loginPasswordEdittext.getText().toString());
            String passwordText = unlocked ? null : "Mot de passe erroné";

            loginPasswordEdittext.setError(passwordText);
            if (unlocked) nextActivity();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(loginButton)) {
            manageAccount();
        }
    }


}
