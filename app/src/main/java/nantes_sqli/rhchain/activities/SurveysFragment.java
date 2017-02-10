package nantes_sqli.rhchain.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import ethereumjava.module.objects.Transaction;
import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.data.Answer;
import nantes_sqli.rhchain.data.Question;
import nantes_sqli.rhchain.data.Results;
import nantes_sqli.rhchain.data.Survey;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by alb on 22/11/16. Display the survey
 */

public class SurveysFragment extends DialogFragment implements View.OnClickListener {

    private Button btn_submButton;
    private ImageButton btnDsQ1, btnNeQ1, btnSaQ1, btnDsQ2, btnNeQ2, btnSaQ2, btnDsQ3, btnNeQ3, btnSaQ3;
    private View viewRoot;
    private Dialog prog;

    private Results results;
    private Survey survey;

    private TextView question1, question2, question3;
    private int questionAnsweredId;

    private  static int[] intitulesQuestion ={R.id.txtQ1,R.id.txtQ2,R.id.txtQ3};
    private  static int[] boutonsQuestion1 = {R.id.btnDsQ1, R.id.btnNeQ1, R.id.btnSaQ1};
    private  static int[] boutonsQuestion2 = {R.id.btnDsQ2, R.id.btnNeQ2, R.id.btnSaQ2};
    private  static int[] boutonsQuestion3 = {R.id.btnDsQ3, R.id.btnNeQ3, R.id.btnSaQ3};
    private static int[][] tableauQuestions = { boutonsQuestion1, boutonsQuestion2, boutonsQuestion3 };
    private static int[] selectedImageQuestions ={R.drawable.ic_dissatisfied_clicked,R.drawable.ic_neutral_clicked, R.drawable.ic_satisfied_clicked};
    private static int[] disabledImageQuestions ={R.drawable.ic_dissatisfied,R.drawable.ic_neutral, R.drawable.ic_satisfied};

    public SurveysFragment() {

    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        SurveyActivity surveyActivity = (SurveyActivity) getActivity();
        this.survey = surveyActivity.getSurvey();
        // todo : user = null
        this.results = new Results(survey, null);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        viewRoot = inflater.inflate(R.layout.fragment_survey, container, false);

        //Chargement des questions
        chargementIntitulesFromSurvey();

        // Gestion clic sur reponse
        initialisationGestionClicReponses();

        // Bouton soummettre désactivé par default
        gestionBoutonSoumission(false);

        btn_submButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Observable<Transaction> o;

                o.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Transaction>() {
                        @Override
                        public void call(Transaction transaction) {
                            //TODO arreter chargement
                        }
                    });

                AlertDialog(savedInstanceState);*/
            }
        });




        return viewRoot;
    }

    @Override
    public void onClick(View v) {
    }

    /**
     * Permet d'initialiser les reflexes des boutons de reponses
     */
    private void initialisationGestionClicReponses() {
        // Parcours des questions paramétrées
        for (int indexQuestion = 0; indexQuestion < tableauQuestions.length; indexQuestion++) {

            //Récuperation de la liste des ID de boutons de la question courante
            final int[] boutonsDeQuestion = SurveysFragment.tableauQuestions[indexQuestion];

            // Parcours des boutonsDeQuestion de la question
            for (int i = 0; i < boutonsDeQuestion.length; i++) {

                // Récupération du bouton courant
                final ImageButton btn = (ImageButton) viewRoot.findViewById(boutonsDeQuestion[i]);

                // Gestion clic sur bouton courant
                final int finalIndexBouton = i;
                final int finalIndexQuestion = indexQuestion;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // RAZ etat des boutons reponses
                        deselectionBoutonsReponse(boutonsDeQuestion);
                        // selection de l'élement cliqué
                        btn.setImageDrawable(ContextCompat.getDrawable(getContext(), selectedImageQuestions[finalIndexBouton]));

                        //ajout de la reponse au vote
                        setVote(finalIndexQuestion, finalIndexBouton);

                        // Verification soumission possible en fonction des reponses donnés
                        gestionBoutonSoumission(isSurveyCompleted());
                    }
                });
            }

        }
    }


    /**
     * Permet de d'activer ou désactiver le clic sur le bouton de soumission + la couleur de celui-ci
     * @param enabled flag true si actif
     */
    private void gestionBoutonSoumission(boolean enabled) {

        btn_submButton = (Button) viewRoot.findViewById(R.id.btn_submit_survey);

        int color = ContextCompat.getColor(getContext(), R.color.lessdarkgrey);
        if(enabled) {
            color = ContextCompat.getColor(getContext(), R.color.colorSqli);
        }

        // MOdification du fond du bouton http://www.41post.com/5094/programming/android-change-color-of-the-standard-button-inside-activity
        btn_submButton.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

        btn_submButton.setEnabled(enabled);
        btn_submButton.invalidate();
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


    /**
     * Permet de déselectionner les boutons d'une question
     * @param boutons la listes des id des boutons a désactiver
     */
    private void deselectionBoutonsReponse(int[] boutons) {
        // Parcours des boutons de la question
        for (int i = 0; i < boutons.length; i++) {
            ImageButton bouton = (ImageButton) viewRoot.findViewById(boutons[i]);
            bouton.setImageDrawable(ContextCompat.getDrawable(getContext(), disabledImageQuestions[i]));
        }
    }

    /**
     * Permet le chargement des intitulés de question depuis l'objet source survey
     */
    private void chargementIntitulesFromSurvey() {
        for (int i = 0; i < intitulesQuestion.length; i++) {
            TextView question = (TextView) viewRoot.findViewById(intitulesQuestion[i]);
            question.setText(this.survey.getQuestion(i).getTextQuestion());
        }
    }

    public void setVote(int question, int idValue){
        Question questionCourante = survey.getQuestion(question);

        // Recuperation de la valeur de la reponse
        Answer reponse  = questionCourante.getAnswer(idValue);

        //enregistrement du resultat courant
        Answer[] reponsesSelectionnees = this.results.getReponseSelectionnees();
        reponsesSelectionnees[question] = reponse;

    }

    public boolean isSurveyCompleted(){
        Answer[] reponsesSelectionnees = this.results.getReponseSelectionnees();
        int nbreponses = 0;
        int nbQuestions = this.survey.getQuestions().size();
        for(int i =0; i < nbQuestions; i++){
            // Si une reponse n'est pas renseignée
            if(null == reponsesSelectionnees[i]){
                return false;
            }
        }
        return true;

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



    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
