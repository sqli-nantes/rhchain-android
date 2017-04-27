package com.sqli.blockchain.rhchain.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.model.Question;
import com.sqli.blockchain.rhchain.model.Results;

import java.util.List;

import static com.sqli.blockchain.rhchain.Constants.NEUTRAL_ID;
import static com.sqli.blockchain.rhchain.Constants.SATISFIED_ID;
import static com.sqli.blockchain.rhchain.Constants.UNSATISFIED_ID;

/**
 * Created by gunicolas on 20/04/17.
 */

public class ResultAdapter extends BaseAdapter {

    List<Question> questions;
    Context context;
    Results results;

    private static LayoutInflater inflater = null;


    public ResultAdapter(Context context, List<Question> questions, Results results) {
        this.questions = questions;
        this.context = context;
        this.results = results;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Question getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    private class Holder{

        TextView questionView;
        ResultSatisfactionView resultSatisfiedView;
        ResultSatisfactionView resultUnsatisfiedView;
        ResultSatisfactionView resultNeutralView;

        Holder(View view){
            this.questionView = (TextView) view.findViewById(R.id.result_question);
            this.resultSatisfiedView = (ResultSatisfactionView) view.findViewById(R.id.result_item_satisfied);
            this.resultUnsatisfiedView = (ResultSatisfactionView) view.findViewById(R.id.result_item_unsatisfied);
            this.resultNeutralView = (ResultSatisfactionView) view.findViewById(R.id.result_item_neutral);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if( view != null ) return view;

        view = inflater.inflate(R.layout.result_item,null);
        final Holder holder = new Holder(view);

        int nbAnswers = results.getNbAnswers();
        int nbUnsatisfied = results.getResultsAt(position)[UNSATISFIED_ID];
        int nbNeutral = results.getResultsAt(position)[NEUTRAL_ID];
        int nbSatisfied = results.getResultsAt(position)[SATISFIED_ID];
        int percentageUnsatisfied = (int) (((double) nbUnsatisfied / (double) nbAnswers)*100);
        int percentageNeutral = (int) (((double) nbNeutral / (double) nbAnswers)*100);
        int percentageSatisfied = (int) (((double) nbSatisfied / (double) nbAnswers)*100);

        holder.resultSatisfiedView.setProgress(percentageSatisfied);
        holder.resultSatisfiedView.setNumber(nbSatisfied);
        holder.resultSatisfiedView.setPercentage(percentageSatisfied);

        holder.resultNeutralView.setProgress(percentageNeutral);
        holder.resultNeutralView.setNumber(nbNeutral);
        holder.resultNeutralView.setPercentage(percentageNeutral);

        holder.resultUnsatisfiedView.setProgress(percentageUnsatisfied);
        holder.resultUnsatisfiedView.setNumber(nbUnsatisfied);
        holder.resultUnsatisfiedView.setPercentage(percentageUnsatisfied);

        holder.questionView.setText(getItem(position).libelle);

        return view;

    }
}
