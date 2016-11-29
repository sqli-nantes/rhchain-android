package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by alb on 14/11/16.
 * User's account creation
 * Use for demo mode
 */
public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_valid;
    EditText userId;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_create_user);

        btn_valid = (Button) findViewById(R.id.btn_valid);
        btn_valid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SetUser();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void SetUser() {
        user.setIdentity(String.valueOf(Log.v(String.valueOf(R.string.txt_userId), userId.getText().toString())));
        user.setPassword(String.valueOf(Log.v(String.valueOf(R.string.txt_pwd), userId.getText().toString())));
    }

}