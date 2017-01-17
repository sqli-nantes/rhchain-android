package nantes_sqli.rhchain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Created by alb on 22/11/16. Display the survey
 */

public class SurveysFragment extends DialogFragment implements View.OnClickListener {

    private Button btn_submButton;
    private ImageButton btnDsQ1, btnNeQ1, btnSaQ1, btnDsQ2, btnNeQ2, btnSaQ2, btnDsQ3, btnNeQ3, btnSaQ3;
    private View viewRoot;
    private Dialog prog;

    public SurveysFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        viewRoot = inflater.inflate(R.layout.fragment_survey, container, false);

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

        btnDsQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtn();
                btnDsQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied_clicked));

            }
        });

        btnNeQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtn();
                btnNeQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral_clicked));

            }
        });

        btnSaQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtn();
                btnSaQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied_clicked));

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

            }
        });

        btnNeQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ2();
                btnNeQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral_clicked));

            }
        });

        btnSaQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ2();
                btnSaQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied_clicked));

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

            }
        });

        btnNeQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ3();
                btnNeQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral_clicked));

            }
        });

        btnSaQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablebtnQ3();
                btnSaQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied_clicked));

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
    public void disablebtn() {
        btnDsQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied));
        btnNeQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral));
        btnSaQ1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied));
    }

    public void disablebtnQ2() {
        btnDsQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied));
        btnNeQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral));
        btnSaQ2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied));
    }

    public void disablebtnQ3() {
        btnDsQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dissatisfied));
        btnNeQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_neutral));
        btnSaQ3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_satisfied));
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
}
