package nantes_sqli.rhchain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import javax.xml.datatype.Duration;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.RhchainApplication;

/**
 * Created by alb on 28/11/16.
 */

public class MenuFragment extends Fragment {

    Fragment fragment;
    FragmentTransaction fragmentTransaction;


    public MenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        fragment = new SurveysFragment();


        fragmentTransaction = getFragmentManager().beginTransaction().add(R.id.container, fragment);
        fragmentTransaction.commit();

        Button btnSurvey = (Button) view.findViewById(R.id.btn_survey);
        Button btnResults = (Button) view.findViewById(R.id.btn_results);


        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new SurveysFragment();
                fragmentTransaction = getFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment);
                fragmentTransaction.commit();

            }
        });

        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RhchainApplication app = (RhchainApplication) getActivity().getApplication();
                boolean closed = app.gethManager.isVoteClosed();
                if( closed ) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ResultatsActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getContext(),"VOTE NOT CLOSED", Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }
}
