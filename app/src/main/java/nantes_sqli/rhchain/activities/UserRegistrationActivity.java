package nantes_sqli.rhchain.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.RhchainApplication;
import nantes_sqli.rhchain.blockchain.GethManager;
import nantes_sqli.rhchain.utils.ButtonUtils;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Button btn_to_surveyfrag;
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
            EditText password1 = (EditText) findViewById(ID_PASSWORD_1);
            EditText password2 = (EditText) findViewById(ID_PASSWORD_2);
            // Verification du remplissage du mot de passe
            if (password1.getText().toString().isEmpty()) {
                // enregistrement KO -> affichage message d'erreur
                Toast.makeText(getApplicationContext(), "Merci de renseigner un mot de passe.", Toast.LENGTH_SHORT).show();
            }

            // Confirmation du mot de passe
            if (password1.getText().toString().equals(password2.getText().toString())) {
                creationCompte(password1);
                navigationToSurvey();
            }else{
                // enregistrement KO -> affichage message d'erreur
                Toast.makeText(getApplicationContext(), " Le mot de passe de confirmation est différent du mot de passe saisi précédemment", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void creationCompte(EditText password1) {
        GethManager gethManager = ((RhchainApplication) getApplication()).gethManager;
        gethManager.createDefaultAccount(password1.getText().toString());
    }

    private void navigationToSurvey() {
//        Survey survey = Bouchonnage.setDemoSurvey();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.putExtra("survey", survey);
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



