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

    Button btn_sub, btn_ok ;
    PopupWindow popupMessage;
    TextView popupText;
    LinearLayout layoutOfPopup;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    Inflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        getSupportFragmentManager().beginTransaction().add(R.id.menuSurvey, new MenuFragment()).commit();
//        init();
//        popupInit();

    }
/*
    public void init() {
        btn_sub = (Button) btn_sub.findViewById(R.id.btn_submit);
        btn_sub = (Button) btn_sub.findViewById(R.id.btn_submit);
        popupText = new TextView(this);
        btn_ok = new Button(this);
        layoutOfPopup = new LinearLayout(this);
        btn_ok.setText("Oui");
        popupText.setText("Validez-vous vos choix?");
        popupText.setPadding(0, 0, 0, 20);
        layoutOfPopup.addView(popupText);
        layoutOfPopup.addView(btn_ok);
    }

    public void popupInit() {
        btn_sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_submit) {
                    popupMessage.showAsDropDown(btn_sub, 0, 0);
                }

                else {
                    popupMessage.dismiss();
                }
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // Mettre ici la transaction fragment
                //FragmentTransaction
            }
        });
        popupMessage = new PopupWindow(layoutOfPopup, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupMessage.setContentView(layoutOfPopup);
    }
    */
}
