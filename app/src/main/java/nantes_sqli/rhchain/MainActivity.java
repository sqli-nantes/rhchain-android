package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Display 2 possibilities: answer survey or create a user's account
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_connect;
    Button btn_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(this);

        btn_account = (Button) findViewById(R.id.btn_creat);
        btn_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case   R.id.btn_connect: {
                Intent intent = new Intent(this, SurveyActivity.class);
                startActivity(intent);
            }
            case R.id.btn_creat:{
                Intent intent = new Intent(this, CreateUserActivity.class);
                startActivity(intent);
            }
        }
    }
}

