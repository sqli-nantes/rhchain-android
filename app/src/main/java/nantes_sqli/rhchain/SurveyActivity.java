package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nantes_sqli.rhchain.data.Survey;

/**
 * Created by alb on 25/11/16.
 */


public class SurveyActivity extends AppCompatActivity {

    private Bundle bundle = new Bundle();
    private Survey survey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Intent intent = getIntent();
        survey = intent.getParcelableExtra("survey");

        bundle.putParcelable("survey",survey);

        MenuFragment menuFrag = new MenuFragment();
        menuFrag.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.menuSurvey, menuFrag).commit();
    }

    public Survey getSurvey() {
        return survey;
    }

}
