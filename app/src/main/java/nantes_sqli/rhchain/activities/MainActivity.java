package nantes_sqli.rhchain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.data.User;
import nantes_sqli.rhchain.utils.Bouchonnage;

/**
 * Display 2 possibilities: answer survey or create a user's account At btn_connect get the survey
 * to be displayed Use Survey class User class
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_connect, btn_account;
    Survey survey;
    User user ;


    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState =savedInstanceState;
        setContentView(R.layout.activity_main);

        // Chargement questionnaire Bouchonné
        survey = Bouchonnage.setDemoSurvey();
        user = Bouchonnage.setDemoUser();
        //user.setIdentity("toto");

        // Initialisation bouton de connection
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(this);


        // Initialisation du bouton  de creation de compte
        btn_account = (Button) findViewById(R.id.btn_account);
        btn_account.setOnClickListener(this);


    }

    /**
     * Gestion des actions de clic sur un bouton
     * @param v la vue cliquée
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect: {

                Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
                intent.putExtra("survey",survey);
/*                doit récupérer les informations users
                user.setIdentity();
                doit vérifier que le password entré est identique aux password enregistrer par la BC
                if (R.id.editTextPassword = user.getPassword()){

                }
*/
                startActivity(intent);
                break;
            }
            case R.id.btn_account: {
                Log.v("étape","choix de la création");
                Intent intent = new Intent(this, UserRegistrationActivity.class);
                startActivity(intent);
                break;
            }

            default:
                break;

        }
    }

}

