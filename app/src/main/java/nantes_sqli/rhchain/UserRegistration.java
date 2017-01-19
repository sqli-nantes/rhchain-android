package nantes_sqli.rhchain;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    Button btn_to_surveyfrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        btn_to_surveyfrag = (Button) findViewById(R.id.btn_to_surveyfrag);
        btn_to_surveyfrag.setOnClickListener(this);

    }


    @Override
    public void onClick(View v){
        if( v == btn_to_surveyfrag ) {
            Intent nextpage = new Intent(this, SurveyActivity.class);
            startActivity(nextpage);
        }

    }


}



