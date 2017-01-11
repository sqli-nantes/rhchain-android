package nantes_sqli.rhchain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by alb on 22/11/16. Display the survey
 */

public class SurveysFragment extends DialogFragment implements View.OnClickListener {

    private Button btn_submButton;
    private View viewRoot;
    private Dialog prog;
    private Survey survey;

    private TextView question1;
    private TextView question2;
    private TextView question3;


    public SurveysFragment() {
    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        SurveyActivity surveyActivity = (SurveyActivity) getActivity();
        survey = surveyActivity.getSurvey();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        setCancelable(false);
        viewRoot = inflater.inflate(R.layout.fragment_survey, container, false);

//        setContentView(R.layout.fragment_survey);
        surveyImport();

        btn_submButton = (Button) viewRoot.findViewById(R.id.btn_submit_survey);

        btn_submButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog(savedInstanceState);
            }
        });


        return viewRoot;
    }

    @Override
    public void onClick(View v) {
    }


    public void AlertDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder ad = new AlertDialog.Builder(viewRoot.getContext());
        ad.setTitle("Etes-vous sur de votre choix?");

        ad.setPositiveButton("OUI",
            new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    prog = onCreateDialog(savedInstanceState);
                    prog.show();
                    launchIntent(prog);
                }
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

    public void launchIntent(final Dialog prog) {
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                prog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        dialog.setTitle(getString(R.string.pleaseWait));
        dialog.setMessage(getString(R.string.writing_BC));
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        return dialog;
    }

//    public void setContentView(int contentView) {
//        TextView textQuestion = new TextView();
//        this.contentView = contentView;
//
//        textQuestion.findViewById(R.id.txtQ1);
//        textQuestion.setText(survey.getQuestion(1).getTextQuestion());
//
//    }

    private void surveyImport() {
        question1 = (TextView) viewRoot.findViewById(R.id.txtQ1);
        question1.setText(survey.getQuestion(0).getTextQuestion());

        question2 = (TextView) viewRoot.findViewById(R.id.txtQ2);
        question2.setText(survey.getQuestion(1).getTextQuestion());

        question3 = (TextView) viewRoot.findViewById(R.id.txtQ3);
        question3.setText(survey.getQuestion(2).getTextQuestion());
    }
}

