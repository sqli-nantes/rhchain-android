package nantes_sqli.rhchain.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nantes_sqli.rhchain.R;


public class BargraphLayout extends RelativeLayout {

    public BargraphLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public BargraphLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public BargraphLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void  init(Context ctx, AttributeSet attrs){
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Lien avec le layout

        View v = li.inflate(R.layout.layout_bargraph, null);
        TextView textRed = (TextView) v.findViewById(R.id.textRed);
        addView(v);

        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.stylable_bargraph);
        String resultRed = a.getString(R.styleable.stylable_bargraph_result_red);
        if(resultRed !=null) {
            textRed.setText(resultRed);
        }

    }
}
