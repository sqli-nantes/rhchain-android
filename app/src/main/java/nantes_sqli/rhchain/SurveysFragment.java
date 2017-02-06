package nantes_sqli.rhchain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import nantes_sqli.rhchain.data.Results;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.data.Vote;


/**
 * Created by alb on 22/11/16. Display the survey
 */

public class SurveysFragment extends DialogFragment implements View.OnClickListener {

    private Button btn_submButton;
    private ImageButton btnDsQ1, btnNeQ1, btnSaQ1, btnDsQ2, btnNeQ2, btnSaQ2, btnDsQ3, btnNeQ3, btnSaQ3;
    private View viewRoot;
    private Dialog prog;
    private Vote vote = new Vote();
    private Results results = new Results();
    private Survey survey;
    private TextView question1, question2, question3;
    private int questionAnsweredId;

    public SurveysFragment() {

    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        SurveyActivity surveyActivity = (SurveyActivity) getActivity();
        survey = surveyActivity.getSurvey();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        viewRoot = inflater.inflate(R.layout.fragment_survey, container, false);

        surveyImport();

        btn_submButton = (Button) viewRoot.findViewById(R.id.btn_submit_survey);
                                    /*
                                    Question N1
                                     */
        btnDsQ1 = (ImageButton) viewRoot.findViewById(R.id.btnDsQ1);
        btnNeQ1 = (ImageButton) viewRoot.findViewById(R.id.btnNeQ1);
        btnSaQ1 = (ImageButton) viewRoot.findViewById(R.id.btnSaQ1);

        btnDsQ2 = (ImageButton) viewRoot.findViewById(R.id.btnDsQ2);
        btnNeQ2 = (ImageButton) viewRoot.findViewById(R.id.btnNeQ2);
        btnSaQ2 = (ImageButton) viewRoot.findViewById(R.id.btnSaQ2);

        btnDsQ3 = (ImageButton) viewRoot.findViewById(R.id.btnDsQ3);
        btnNeQ3 = (ImageButton) viewRoot.findViewById(R.id.btnNeQ3);
        btnSaQ3 = (ImageButton) viewRoot.findViewById(R.id.btnSaQ3);

        btn_submButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog(savedInstanceState);
            }
        });

//        Gestion de l'affichage des icônes réponse.
        btnDsQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ1();
                btnDsQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied_clicked));

                setVote(0);

            }
        });

        btnNeQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ1();
                btnNeQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral_clicked));

                setVote(1);

            }
        });

        btnSaQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ1();
                btnSaQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied_clicked));

                setVote(2);

                Log.d("SurveysFragment _ vote", "onClick: " + vote.getValue() + " / " + vote.getQuestionId());

            }
        });
                                        /*
                                        Question N2
                                         */
        btnDsQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ2();
                btnDsQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied_clicked));

                setVote(0);

            }
        });

        btnNeQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ2();
                btnNeQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral_clicked));

                setVote(1);

            }
        });

        btnSaQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ2();
                btnSaQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied_clicked));

                setVote(2);

            }
        });

                                        /*
                                        Question N3
                                         */

        btnDsQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ3();
                btnDsQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied_clicked));

                setVote(0);

            }
        });

        btnNeQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ3();
                btnNeQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral_clicked));

                setVote(1);

            }
        });

        btnSaQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ3();
                btnSaQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied_clicked));

                setVote(2);

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
    public void disablebtnQ1() {
        questionAnsweredId = 0;
        btnDsQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied));
        btnNeQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral));
        btnSaQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied));
    }

    public void disablebtnQ2() {
        questionAnsweredId = 1;
        btnDsQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied));
        btnNeQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral));
        btnSaQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied));
    }

    public void disablebtnQ3() {
        questionAnsweredId = 2;
        btnDsQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied));
        btnNeQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral));
        btnSaQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied));
    }

    public void checkCompleted(){

    }

    public void setVote(int idValue){
        vote.setQuestionId(survey.getQuestion(questionAnsweredId).getId());
        vote.setValue(survey.getQuestion(questionAnsweredId).getAnswer(idValue).getValue());

//     Verifier si le table est null

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

    private void surveyImport() {
        question1 = (TextView) viewRoot.findViewById(R.id.txtQ1);
        question1.setText(survey.getQuestion(0).getTextQuestion());

        question2 = (TextView) viewRoot.findViewById(R.id.txtQ2);
        question2.setText(survey.getQuestion(1).getTextQuestion());

        question3 = (TextView) viewRoot.findViewById(R.id.txtQ3);
        question3.setText(survey.getQuestion(2).getTextQuestion());
        }
}
