package com.sqli.blockchain.rhchain.results;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.RHChainAbstractActivity;
import com.sqli.blockchain.rhchain.model.Results;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by gunicolas on 19/04/17.
 */

public class ResultActivity extends RHChainAbstractActivity {

    ListView resultListview;
    TextView resultTotalTextview;
    TextView resultDateTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        resultListview = (ListView) findViewById(R.id.result_listview);
        int size = application.questions.size();
        Results results = application.blockchainAPI.getResults(size,size); //TODO temporary answer size
        resultListview.setAdapter(new ResultAdapter(this,application.questions,results));

        resultTotalTextview = (TextView) findViewById(R.id.result_total_textview);
        resultTotalTextview.setText(String.valueOf(results.getNbAnswers())+" Réponses");

        resultDateTextview = (TextView) findViewById(R.id.result_date_textview);

        //Utils.getCurrentLocale(this);
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy",Locale.FRANCE );
        String dateString = df.format(Calendar.getInstance().getTime());


        resultDateTextview.setText("Sondage du "+dateString);


    }
}
