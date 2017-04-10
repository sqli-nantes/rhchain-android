package nantes_sqli.rhchain.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.data.QuestionResultat;

public class QuestionResultatAdapter extends ArrayAdapter<QuestionResultat> {

    public static final int LARGEUR_MINIMALE = 50;

    //tweets est la liste des models à afficher
    public QuestionResultatAdapter(Context context, List<QuestionResultat> resultats) {
        super(context, 0, resultats);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_question_resultats, parent, false);
        }
        QuestionResultatHolder viewHolder = (QuestionResultatHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new QuestionResultatHolder();
            viewHolder.question = (TextView) convertView.findViewById(R.id.question);
            viewHolder.bargraph = (BargraphLayout) convertView.findViewById(R.id.barchart);

            convertView.setTag(viewHolder);
        }

        QuestionResultat questionResultat = getItem(position);
        viewHolder.question.setText(questionResultat.getQuestion());
        initBarChart(viewHolder.bargraph, questionResultat.getResultats());
        return convertView;
    }



    private static class QuestionResultatHolder {
        public TextView question;
        public BargraphLayout bargraph;
    }


    private void initBarChart(View barChartView, Integer[] listeResultats) {
        //process resultats
        Integer sommeResultats = getSommeResultats(listeResultats);
        List<Float> listePourcentageResultat = new ArrayList<Float>();
        for (Integer resultat : listeResultats) {
            listePourcentageResultat.add(new Float(resultat) / sommeResultats);
        }

        List<View> listeColonnes = new ArrayList<>();
        listeColonnes.add((View) barChartView.findViewById(R.id.GreenLayout));
        listeColonnes.add((View) barChartView.findViewById(R.id.OrangeLayout));
        listeColonnes.add((View) barChartView.findViewById(R.id.Redlayout));

        Integer widthAvailable = calculateAvailableWidth(barChartView);

        for (int i = 0; i < listeColonnes.size(); i++) {
            Float colonneWidth = listePourcentageResultat.get(i) * widthAvailable;
            String colonneLibelle = listeResultats[i].toString();
            scaleAndSetTextBarChart(listeColonnes.get(i), colonneWidth, colonneLibelle);
        }

    }

    private void scaleAndSetTextBarChart(View view, Float colonneWidth, String colonneLibelle) {

        // Ajout largeur minimal
        colonneWidth += dpToPx(view, LARGEUR_MINIMALE);
        setLayoutSize(view, colonneWidth.intValue());

        // Find textView
        for (int index = 0; index < ((ViewGroup) view).getChildCount(); ++index) {
            View nextChild = ((ViewGroup) view).getChildAt(index);
            if (nextChild instanceof TextView) {
                TextView textView = (TextView) nextChild;
                textView.getId();
                textView.setText(colonneLibelle);
            }
        }
    }


    private Integer calculateAvailableWidth(View view) {
        int minWidth = 3 * dpToPx(view, LARGEUR_MINIMALE);

        int largeurParent = ((LinearLayout) view.getParent()).getLayoutParams().width;
        //Largeur non fixé on prend la largeur de l'écran
        if (largeurParent == -1) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            largeurParent = displayMetrics.widthPixels;
        }
        return largeurParent - minWidth;
    }

    private Integer getSommeResultats(Integer[] listeResultats) {
        Integer sommeresultats = 0;
        for (Integer resultats : listeResultats) {
            sommeresultats += resultats;
        }
        return sommeresultats;
    }

    private int dpToPx(View view, int dp) {
        return (int) (dp * view.getResources().getDisplayMetrics().density + 0.5f);
    }

    private static void setLayoutSize(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

}
