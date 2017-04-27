package com.sqli.blockchain.rhchain.results;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sqli.blockchain.rhchain.R;

/**
 * Created by gunicolas on 20/04/17.
 */

public class ResultSatisfactionView extends LinearLayout {

    private static final char PERCENTAGE_CHAR = '%';

    LinearLayout rootView;
    ImageView iconImageview;
    ProgressBar progressBar;
    TextView numberTextview;
    TextView percentageTextview;

    public ResultSatisfactionView(Context context) {
        super(context);
        init(context);
    }

    public ResultSatisfactionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ResultSatisfactionView,0,0);
        Drawable src = ta.getDrawable(R.styleable.ResultSatisfactionView_icon);
        iconImageview.setImageDrawable(src);
    }

    private void init(Context context){
        rootView = (LinearLayout) inflate(context,R.layout.result_satisfaction_item,this);
        iconImageview = (ImageView) rootView.findViewById(R.id.result_satisfaction_icon);
        progressBar = (ProgressBar) rootView.findViewById(R.id.result_satisfaction_progress);
        numberTextview = (TextView) rootView.findViewById(R.id.result_satisfaction_number);
        percentageTextview = (TextView) rootView.findViewById(R.id.result_satisfaction_percentage);
    }

    public int getProgress(){
        return progressBar.getProgress();
    }
    public void setProgress(int progress){
        progressBar.setProgress(progress);
    }
    public int getNumber(){
        return Integer.valueOf(numberTextview.getText().toString());
    }
    public void setNumber(int number) throws NumberFormatException{
        numberTextview.setText(String.valueOf(number));
    }
    public int getPercentage() throws NumberFormatException {
        String percentageStr = percentageTextview.getText().toString();
        String percentage = percentageStr.replace(String.valueOf(PERCENTAGE_CHAR),"");
        return Integer.valueOf(percentage);
    }
    public void setPercentage(int percentage){
        percentageTextview.setText(String.valueOf(percentage)+PERCENTAGE_CHAR);
    }

}
