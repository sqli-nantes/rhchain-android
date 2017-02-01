package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Display 2 possibilities: answer survey or create a user's account At btn_connect get the survey
 * to be displayed Use Survey class User class
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_connect, btn_account;
    Survey survey = new Survey();
    User user = new User();
    Bouchonnage demoBouchon = new Bouchonnage();

    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState =savedInstanceState;
        setContentView(R.layout.activity_main);


        btn_connect = (Button) findViewById(R.id.btn_connect);
//        setSurvey(survey);
        survey = demoBouchon.setDemoSurvey();
        user = demoBouchon.setDemoUser();

        btn_connect.setOnClickListener(this);

//        user.setIdentity("toto");


//      Utilisable lors de la mise en prod de la fonction reaction compte
        btn_account = (Button) findViewById(R.id.btn_account);
        btn_account.setOnClickListener(this);


    }

//           todo récupérer les informations users
//                startActivity(intent);

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
                Intent intent = new Intent(this, UserRegistration.class);
                startActivity(intent);
                break;
            }

            default:
                break;

        }
    }

}

