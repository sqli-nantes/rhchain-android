package nantes_sqli.rhchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SurveyActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_soum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        btn_soum = (Button) findViewById(R.id.btn_soum);
        btn_soum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }
}
