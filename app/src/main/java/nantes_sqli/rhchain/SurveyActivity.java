package nantes_sqli.rhchain;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class SurveyActivity extends AppCompatActivity implements View.OnClickListener{
//    Button btn_soum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        this.findViewById(R.id.btn_soum).setOnClickListener(this);

/*
        btn_soum = (Button) findViewById(R.id.btn_soum);
        btn_soum.setOnClickListener(this);
        }
*/

    }

    @Override
    public void onClick(View v) {
        //TODO something
        AlertDialog();
    }


    public void AlertDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Etes-vous sur de votre choix?");
    //    ad.setMessage("DO YOU SEE A MSG?");


        ad.setPositiveButton("OUI",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        //TODO redirect to progress bar
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
}
