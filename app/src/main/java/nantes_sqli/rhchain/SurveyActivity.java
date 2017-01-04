package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by alb on 25/11/16.
 */


public class SurveyActivity extends AppCompatActivity {

    private Bundle bundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Intent intent = getIntent();
        Survey survey = intent.getParcelableExtra("survey");
        Log.d("survey", "onCreate: " + survey.getQuestion(1).getTextQuestion());

        bundle.putParcelable("survey",survey);

        getSupportFragmentManager().beginTransaction().add(R.id.menuSurvey, new MenuFragment()).commit();
    }

}
