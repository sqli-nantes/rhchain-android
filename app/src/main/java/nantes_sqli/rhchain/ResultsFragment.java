package nantes_sqli.rhchain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultsFragment extends Fragment {

    private BarChart chartQ1;
    private BarChart chartQ2;
    private BarChart chartQ3;

    public ResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, viewGroup, false);
        return view;

        //TODO put something

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartQ1 = (HorizontalBarChart) view.findViewById(R.id.chartQ1);

        chartQ2 = (HorizontalBarChart) view.findViewById(R.id.chartQ2);

        chartQ3 = (HorizontalBarChart) view.findViewById(R.id.chartQ3);

        mockResultSurveyDemo();
    }

    /**
     * For demo only
     *
     */

    private void mockResultSurveyDemo() {
        ArrayList<Integer> colors = new ArrayList<Integer>();

        //Résultat pour les 3 questions du sondage.

        ArrayList<Integer> nbVoteForQuestion1 = new ArrayList<Integer>();
        nbVoteForQuestion1.addAll(Arrays.asList(1,1,2));

        ArrayList<Integer> nbVoteForQuestion2 = new ArrayList<Integer>();
        nbVoteForQuestion2.addAll(Arrays.asList(1,2,3));

        ArrayList<Integer> nbVoteForQuestion3 =new ArrayList<Integer>();
        nbVoteForQuestion3.addAll(Arrays.asList(2,6,7));

        BarData barData = new BarData();


//La fonction de récupération des pourcentage est à créer

//       List<BarEntry> entries = new ArrayList<BarEntry>();

//       colors.add(R.color.unsatisfied);
//       colors.add(R.color.neutral);
//       colors.add(R.color.satisfied);

        barData = createBarChartObjectStacked(nbVoteForQuestion1, colors);
        chartQ1.setData(barData);

//        barData = createBarChartObject(nbVoteForQuestion1, colors);
//        chartQ1.setData(barData);

        barData = createBarChartObjectStacked(nbVoteForQuestion2, colors);
        chartQ2.setData(barData);

        barData = createBarChartObjectStacked(nbVoteForQuestion3, colors);
        chartQ3.setData(barData);

    }

    /**
     * creation of the dataset use by barchart
     *
     * @param values ArrayList<integer> values to be display on the barchart
     * @param colors ArrayList<integer> of identifier of the colors to be displayed. The color must
     *               been declare in @color
     * @return objet type bardata setted to be display by BarChart object
     */

//Prepare date for classic BarChart
//    private BarData createBarChartObject(ArrayList<Integer> values, ArrayList<Integer> colors) {
//
//        List<BarEntry> entries = new ArrayList<BarEntry>();
//
//        for (int i = 0; i < values.size(); i++) {
//            entries.add(new BarEntry(((float) i), (float) values.get(i)));
//
//
//        }
//
//        BarDataSet dataSet = new BarDataSet(entries, "");
//        dataSet.setColors(new int[] {R.color.unsatisfied, R.color.neutral, R.color.satisfied}, getContext());
//
//
//        BarData barData = new BarData(dataSet);
//
//        return barData;
//    }


    /**
     * creation of the dataset use by barchart stacked
     *
     * @param values ArrayList<integer> values to be display on the barchart
     * @param colors ArrayList<integer> of identifier of the colors to be displayed. The color must
     *               been declare in @color
     * @return objet type bardata setted to be display by stacked BarChart object
     */



//For Stacked BarChart

    private BarData createBarChartObjectStacked(ArrayList<Integer> values, ArrayList<Integer> colors) {

        List<BarEntry> entries = new ArrayList<>();

        float[] valuesFloat = convertIntToFloat(values);
        entries.add(new BarEntry(0f, valuesFloat));
        entries.add(new BarEntry(0f, valuesFloat));
        entries.add(new BarEntry(0f, valuesFloat));


        BarDataSet dataSet = new BarDataSet(entries, " ");

//        for (int i = 1; i <= colors.size(); i++) {
//            dataSet.addColor(colors.get(i));
//        }

        BarData barData = new BarData(dataSet);
        dataSet.setColors(new int[] {R.color.unsatisfied, R.color.neutral, R.color.satisfied}, getContext());

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


//
// TODO Import des Results
// TODO Extraction vote user
// TODO Calcul pourcentage réponse pour chaque question
// TODO Affichage icone choix user et camembert


}
