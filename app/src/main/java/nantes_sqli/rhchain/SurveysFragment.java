package nantes_sqli.rhchain;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.widget.PopupWindowCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;

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
    public class SurveysFragment extends Fragment implements View.OnClickListener{

    public SurveysFragment(){}

    private Button btn_submButton ;
    private View viewRoot;

    @Override
        public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_survey, container,false);

        btn_submButton = (Button) viewRoot.findViewById(R.id.btn_submit_survey);

        btn_submButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog();
            }
        });

        return viewRoot;
    }

    @Override
    public void onClick(View v) {
        //TODO something
        AlertDialog();
    }


    public void AlertDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(viewRoot.getContext());
       ad.setTitle("Etes-vous sur de votre choix?");

       ad.setPositiveButton("OUI",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        launchIntent();                                  }
                }
        );
        ad.setNegativeButton("NON",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        //TODO
                    }
                }
        );
        ad.show();
    }

    public void launchIntent() {

        Fragment fragment;
        FragmentTransaction fragmentTransaction;

        fragment = new ProgressBarFragment();
        fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.container,fragment);
        fragmentTransaction.commit();

    }

}

