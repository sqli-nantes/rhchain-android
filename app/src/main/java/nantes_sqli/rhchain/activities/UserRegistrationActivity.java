package nantes_sqli.rhchain.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.RhchainApplication;
import nantes_sqli.rhchain.blockchain.ApiRetrofit;
import nantes_sqli.rhchain.blockchain.GethManager;
import nantes_sqli.rhchain.blockchain.User;
import nantes_sqli.rhchain.utils.ButtonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Button btn_to_surveyfrag;
    private static int ID_MAIL = R.id.editText4;
    private static int ID_PASSWORD_1 = R.id.editText9;
    private static int ID_PASSWORD_2 = R.id.editText10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        btn_to_surveyfrag = (Button) findViewById(R.id.btn_to_surveyfrag);
        btn_to_surveyfrag.setOnClickListener(this);

        ButtonUtils.modifierEtatBouton(this, btn_to_surveyfrag, false);

        EditText password1 = (EditText) findViewById(ID_PASSWORD_1);
        EditText password2 = (EditText) findViewById(ID_PASSWORD_2);
        password1.addTextChangedListener(this);
        password2.addTextChangedListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == btn_to_surveyfrag) {
            // Verification egalité des mot de passe
            EditText mail = (EditText) findViewById(ID_MAIL);
            EditText password1 = (EditText) findViewById(ID_PASSWORD_1);
            EditText password2 = (EditText) findViewById(ID_PASSWORD_2);

            // Verification du remplissage du mot de passe
            if (password1.getText().toString().isEmpty()) {
                // enregistrement KO -> affichage message d'erreur
                Toast.makeText(getApplicationContext(), "Merci de renseigner un mot de passe.", Toast.LENGTH_SHORT).show();
            }

            //verification du mail
            if (mail.getText().toString().isEmpty()) {
                // enregistrement KO -> affichage message d'erreur
                Toast.makeText(getApplicationContext(), "Merci de renseigner votre adresse mail.", Toast.LENGTH_SHORT).show();
            }

            // Confirmation du mot de passe
            if (password1.getText().toString().equals(password2.getText().toString())) {
                creationCompte(mail, password1);
                navigationToSurvey();
            }else{
                // enregistrement KO -> affichage message d'erreur
                Toast.makeText(getApplicationContext(), " Le mot de passe de confirmation est différent du mot de passe saisi précédemment", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void creationCompte(EditText mail, EditText password1) {
        GethManager gethManager = ((RhchainApplication) getApplication()).gethManager;
        String newAccountAdress = gethManager.createDefaultAccount(password1.getText().toString());

        // Transmission du compte au serveur HTTP
        final User user = new User();
        user.setMail(mail.getText().toString());
        user.setAdress(newAccountAdress);

        final ApiRetrofit apiRetrofit = new ApiRetrofit();

        // APPEL asychrone de l'enregistrement de l'utilisateur
        creationUtilisateur(user, apiRetrofit);



    }

    private void creationUtilisateur(final User user, final ApiRetrofit apiRetrofit) {
        final Call<User> createUserCall = apiRetrofit.getUsersService().getUser(user.getMail());
        createUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " L'utilisateur est existant", Toast.LENGTH_SHORT).show();
                } else if(response.code() == 404){
                    final Call<User> createUserCall = apiRetrofit.getUsersService().createUser(user);
                    createUserCall.enqueue(getCreationUserCallback());
                }
                else{

                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("USER REGISTRATION",t.getMessage());
            }
        });
    }

    private Callback<User> getCreationUserCallback() {
        return new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Enregistrement de l'utilisateur OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Erreur lors de l'enregistrement de l'utilisateur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " Erreur lors de l'enregistrement de l'utilisateur", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void navigationToSurvey() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        View btn_submButton = findViewById(R.id.btn_to_surveyfrag);
        EditText password1 = (EditText) findViewById(ID_PASSWORD_1);
        EditText password2 = (EditText) findViewById(ID_PASSWORD_2);
        if (!password1.getText().toString().isEmpty() &&
            !password2.getText().toString().isEmpty()) {
            ButtonUtils.modifierEtatBouton(this, btn_submButton, true);
        }
        else{
            ButtonUtils.modifierEtatBouton(this, btn_submButton, false);
        }
    }
}



