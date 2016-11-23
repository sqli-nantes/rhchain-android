package nantes_sqli.rhchain;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by alb on 22/11/16.
 * Display the survey
 */

public class SurveysFragment extends Fragment implements View.OnClickListener {
    Button btn_sub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey, viewGroup,false);

        btn_sub = (Button) view.findViewById(R.id.btn_soum);

        btn_sub.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
//        inflater.inflate(R.layout.fragment_survey, v,false);
//        Intent intent = new Intent(this, ResultsFragment);
//        startActivity(intent);
    }
}
