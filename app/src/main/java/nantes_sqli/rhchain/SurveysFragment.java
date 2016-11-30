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

    Button btn_sub ;

    @Override
//    public View onCreate( Bundle savedInstanceState) {
        public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_survey, viewGroup,false);

        btn_sub = (Button)viewRoot.findViewById(R.id.btn_submit_survey);


        btn_sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CharSequence text = "ceci est temporaire";

                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

        return viewRoot;
    }

}

