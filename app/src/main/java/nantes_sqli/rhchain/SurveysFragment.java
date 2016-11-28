package nantes_sqli.rhchain;

import android.content.Context;
import android.os.Bundle;
//import android.support.v4.widget.PopupWindowCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import static android.R.attr.duration;
import static android.R.attr.fragment;

/**
 * Created by alb on 22/11/16.
 * Display the survey
 */

//public class SurveysFragment extends AppCompatActivity implements View.OnClickListener {
    public class SurveysFragment extends Fragment {

    public SurveysFragment(){}

    Context context =  getActivity();
//    Context context = getApplicationContext();
    Button btn_sub, btn_ok ;
//    PopupWindow popupMessage;
//    TextView popupText;
//    LinearLayout layoutOfPopup;
//    View view;

    @Override
//    public View onCreate( Bundle savedInstanceState) {
        public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View viewRoot = (View) inflater.inflate(R.layout.fragment_survey, viewGroup,false);
//        final Fragment fragment;
//        final android.app.FragmentTransaction fragmentTransaction;
//        init();
//        popupInit();

//        Log.i("layout","entrée fragment Survey");

        btn_sub = (Button)viewRoot.findViewById(R.id.btn_submit_survey);

//        Log.v("bouton","initialisation bouton submit");
//

        btn_sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("popup","initialisation du popup");
                CharSequence text = "ceci est temporaire";

                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

               /* fragment = new ResultsFragment();
                fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.surveyFragment,fragment);
                fragmentTransaction.commit(); */

/*                if (v.getId() == R.id.btn_submit_survey) {
                    popupMessage.showAsDropDown(btn_sub, 0, 0);
                }

                else {
                    popupMessage.dismiss();
                }
*/
//                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });

//        return inflater.inflate(R.layout.fragment_survey, viewGroup,false);
        return viewRoot;
//        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

/*      public void init() {
        btn_sub = (Button) btn_sub.findViewById(R.id.btn_submit_survey);
        popupText = new TextView(context);
        btn_ok = new Button(context);
        layoutOfPopup = new LinearLayout(context);
        btn_ok.setText("Oui");
        popupText.setText("Validez-vous vos choix?");
        popupText.setPadding(0, 0, 0, 20);
        layoutOfPopup.addView(popupText);
        layoutOfPopup.addView(btn_ok);
    }

   public void popupInit() {
        btn_sub.setOnClickListener(onClick(view));
        btn_ok.setOnClickListener(context);
        popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        popupMessage.setContentView(layoutOfPopup);
    }

    public saveVote() throws InterruptedException {
        // Enregistrement des votes dans l'objet vote
        // Enregistrement dans BC
        // icone load en attendant
        Vote vote = new Vote();

        vote.setQuestionId();
        ...


        ProgressBar progressBar = new ProgressBar(context);

        progressBar.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                Log.v("methode","passage dans saveVote");

        Thread.sleep(5000);
        progressBar.findViewById(R.id.loadingPanel).setVisibility(View.GONE);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_submit_survey) {
            popupMessage.showAsDropDown(btn_sub, 0, 0);
        }

        else {
            popupMessage.dismiss();
        }
    }
*/

}

