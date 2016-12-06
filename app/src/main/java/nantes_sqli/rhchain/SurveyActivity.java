package nantes_sqli.rhchain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alb on 25/11/16.
 */


public class SurveyActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        getSupportFragmentManager().beginTransaction().add(R.id.menuSurvey, new MenuFragment()).commit();
    }

}
