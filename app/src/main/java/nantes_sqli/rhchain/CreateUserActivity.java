package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.data.User;
import nantes_sqli.rhchain.utils.Bouchonnage;

/**
 * Created by alb on 14/11/16. User's account creation Use for demo mode not use, not tested
 */

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_valid;

    private EditText userId;
    private User user;
    private Survey survey = new Survey();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.v("étape", "entrée activité creation");
        setContentView(R.layout.activity_create_user);

        btn_valid = (Button) findViewById(R.id.btn_valid);
        btn_valid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.user = initUser();
        this.survey = Bouchonnage.setDemoSurvey();

        Intent intent = new Intent(this, SurveyActivity.class);
        intent.putExtra("User", user);
        intent.putExtra("survey", survey);
        startActivity(intent);
    }

    public User initUser() {
        User user = new User();
//        user.setIdentity(String.valueOf(Log.v(String.valueOf(R.string.txt_userId), userId.getText().toString())));
        user.setIdentity(String.valueOf(userId.getText().toString()));
        user.setPassword(String.valueOf(Log.v(String.valueOf(R.string.txt_pwd), userId.getText().toString())));
        return user;
    }

}
