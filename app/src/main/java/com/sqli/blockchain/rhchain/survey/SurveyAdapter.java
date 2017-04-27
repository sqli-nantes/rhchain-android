package com.sqli.blockchain.rhchain.survey;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sqli.blockchain.rhchain.R;
import com.sqli.blockchain.rhchain.model.Question;

import java.util.List;

import static com.sqli.blockchain.rhchain.Constants.NEUTRAL_ID;
import static com.sqli.blockchain.rhchain.Constants.SATISFIED_ID;
import static com.sqli.blockchain.rhchain.Constants.UNSATISFIED_ID;

/**
 * Created by gunicolas on 19/04/17.
 */

class SurveyAdapter extends BaseAdapter  {

    int[] submission;

    private List<Question> surveyList;
    private static LayoutInflater inflater = null;
    private boolean canUpdate;

    SurveyAdapter(Context context, List<Question> surveyList, int[] submission) {
        this.surveyList = surveyList;
        this.submission = submission;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return surveyList.size();
    }

    @Override
    public Question getItem(int position) {
        return surveyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }


    private class Holder {

        private static final float DEFAULT_SCALE = 0.5f;
        private static final float CLICKED_SCALE = 1f;

        TextView questionView;
        ImageView unsatisfiedView;
        ImageView neutralView;
        ImageView satisfiedView;

        Holder(View view){
            questionView = (TextView) view.findViewById(R.id.item_question);
            unsatisfiedView = (ImageView) view.findViewById(R.id.item_unsatisfied);
            neutralView = (ImageView) view.findViewById(R.id.item_neutral);
            satisfiedView = (ImageView) view.findViewById(R.id.item_satisfied);
        }

        private void initClick(){
            unsatisfiedView.setScaleX(DEFAULT_SCALE);
            unsatisfiedView.setScaleY(DEFAULT_SCALE);
            satisfiedView.setScaleX(DEFAULT_SCALE);
            satisfiedView.setScaleY(DEFAULT_SCALE);
            neutralView.setScaleX(DEFAULT_SCALE);
            neutralView.setScaleY(DEFAULT_SCALE);
        }

        void setClicked(int id,int position){
            if( canUpdate ) {
                initClick();
                switch (id) {
                    case SATISFIED_ID:
                        satisfiedView.setScaleX(CLICKED_SCALE);
                        satisfiedView.setScaleY(CLICKED_SCALE);
                        break;
                    case NEUTRAL_ID:
                        neutralView.setScaleX(CLICKED_SCALE);
                        neutralView.setScaleY(CLICKED_SCALE);
                        break;
                    case UNSATISFIED_ID:
                        unsatisfiedView.setScaleX(CLICKED_SCALE);
                        unsatisfiedView.setScaleY(CLICKED_SCALE);
                        break;
                    default:
                        break;
                }
                submission[position] = id;
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if( view != null ) return view;

        view = inflater.inflate(R.layout.survey_item,null);
        final Holder holder = new Holder(view);
        holder.questionView.setText(getItem(position).libelle);
        holder.satisfiedView.setOnClickListener(v -> {
            Log.d("RHCHAIN","satisfied clicked : "+position);
            holder.setClicked(SATISFIED_ID,position);
        });
        holder.neutralView.setOnClickListener(v -> {
            Log.d("RHCHAIN","neutral clicked : "+position);
            holder.setClicked(NEUTRAL_ID,position);
        });
        holder.unsatisfiedView.setOnClickListener(v -> {
            Log.d("RHCHAIN","unsatisfied clicked : "+position);
            holder.setClicked(UNSATISFIED_ID,position);
        });

        holder.setClicked(submission[position], position);

        return view;
    }

    public void lockUpdate(boolean lock) {
        this.canUpdate = !lock;
    }
}
