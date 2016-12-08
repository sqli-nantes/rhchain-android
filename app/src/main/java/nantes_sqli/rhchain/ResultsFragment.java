package nantes_sqli.rhchain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultsFragment extends Fragment {

    private BarChart chartQ1;
    private HorizontalBarChart chartQ2;

    public ResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, viewGroup, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartQ1 = (BarChart) view.findViewById(R.id.chartQ1);
        //        chart.setDescription("Q1");
//        chart.setOnChartValueSelectedListener((OnChartValueSelectedListener) viewGroup);
//        Log.d("debbug", "onCreateView: chart");
//        chart.getDescription().setEnabled(false);

        chartQ2 = (HorizontalBarChart) view.findViewById(R.id.chartQ2);

        mockResultSurveyDemo();
    }

    /**
     * For demo only
     */
    private void mockResultSurveyDemo() {

        ArrayList<Integer> colors = new ArrayList<Integer>();

//        Résultat pour les 3 questions du sondage.
        ArrayList<Integer> nbVoteForQuestion1 = new ArrayList<Integer>();
        nbVoteForQuestion1.addAll(Arrays.asList(2, 5, 3));

        ArrayList<Integer> nbVoteForQuestion2 = new ArrayList<Integer>();
        nbVoteForQuestion2.addAll(Arrays.asList(1, 3, 6));

        int[] nbVoteForQuestion3 = {8, 0, 2};

        BarData barData = new BarData();
//        La fonction de récupération des pourcentage est à créer

        List<BarEntry> entries = new ArrayList<BarEntry>();


        colors.add(R.color.unsatisfied);
        colors.add(R.color.neutral);
        colors.add(R.color.satisfied);

        barData = createBarChartObject(nbVoteForQuestion1, colors);
        chartQ1.setData(barData);

        barData = createBarChartObjectStacked(nbVoteForQuestion2, colors);
        chartQ2.setData(barData);

    }

    /**
     * creation of the dataset use by barchart
     *
     * @param values ArrayList<integer> values to be display on the barchart
     * @param colors ArrayList<integer> of identifier of the colors to be displayed. The color must
     *               been declare in @color
     * @return objet type bardata setted to be display by BarChart object
     */
    //    Prepare date for classic BarChart
    private BarData createBarChartObject(ArrayList<Integer> values, ArrayList<Integer> colors) {

        List<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < values.size(); i++) {
            entries.add(new BarEntry(((float) i), (float) values.get(i)));
        }

//        for (int i =0; i<values.size() ; i++) {
//            entries.add(new BarEntry(values.get(i) ,i+1  ));
//        }

        BarDataSet dataSet = new BarDataSet(entries, "");

        for (int i = 0; i < colors.size(); i++) {
            dataSet.addColor(colors.get(i));
        }

        BarData barData = new BarData(dataSet);

        return barData;
    }

    /**
     * creation of the dataset use by barchart stacked
     *
     * @param values ArrayList<integer> values to be display on the barchart
     * @param colors ArrayList<integer> of identifier of the colors to be displayed. The color must
     *               been declare in @color
     * @return objet type bardata setted to be display by stacked BarChart object
     */
    //    For Stacked BarChart
    private BarData createBarChartObjectStacked(ArrayList<Integer> values, ArrayList<Integer> colors) {

        List<BarEntry> entries = new ArrayList<BarEntry>();
//        for (i =0; i<values.size() ; i++) {
//            entries.add(new BarEntry(i+1 , values.get(i)));
//
//  }
        float[] valuesFloat = convertIntToFloat(values);
        entries.add(new BarEntry(1f, valuesFloat));
        entries.add(new BarEntry(2f, valuesFloat));

        BarDataSet dataSet = new BarDataSet(entries, "y'a quelque chose qui passe?");

        for (int i = 0; i < colors.size(); i++) {
            dataSet.addColor(colors.get(i));
        }

        BarData barData = new BarData(dataSet);

        return barData;
    }

    /**
     * convert a list of integer to an array of float
     *
     * @param arr list of Integer
     * @return array of float
     */
    public float[] convertIntToFloat(List<Integer> arr) {
        float[] arrResult = new float[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            arrResult[i] = arr.get(i);
        }
        return arrResult;
    }
// TODO Import des Results
// TODO Extraction vote user
// TODO Calcul pourcentage réponse pour chaque question
// TODO Affichage icone choix user et camembert
}
