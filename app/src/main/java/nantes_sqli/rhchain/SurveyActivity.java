package nantes_sqli.rhchain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.zip.Inflater;

/**
 * Created by alb on 25/11/16.
 */


public class SurveyActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        getSupportFragmentManager().beginTransaction().add(R.id.menuSurvey, new MenuFragment()).commit();
    }

}
