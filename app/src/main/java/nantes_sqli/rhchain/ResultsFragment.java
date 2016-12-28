package nantes_sqli.rhchain;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class ResultsFragment extends Fragment {


    public ResultsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, viewGroup, false);

        GraphView graph1 = (GraphView) view.findViewById(R.id.graph1);
        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[] {

            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
        });
        graph1.addSeries(series1);

// styling
        series1.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/6, (int) Math.abs(data.getY()*255/6), 650);
            }
        });

        series1.setSpacing(10);

// draw values on top
        series1.setDrawValuesOnTop(true);
        series1.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);


        GraphView graph2 = (GraphView) view.findViewById(R.id.graph2);
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 6),
            new DataPoint(2, 2),
            new DataPoint(3, 3),
            new DataPoint(4, 5)
        });
        graph2.addSeries(series2);

// styling
        series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/6, (int) Math.abs(data.getY()*255/6), 200);
            }
        });

        series2.setSpacing(10);

// draw values on top
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);

        GraphView graph3 = (GraphView) view.findViewById(R.id.graph3);
        BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 6),
            new DataPoint(1, 1),
            new DataPoint(2, 5),
            new DataPoint(3, 2),
            new DataPoint(4, 3)
        });
        graph3.addSeries(series3);

// styling
        series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/6, (int) Math.abs(data.getY()*255/6), 500);
            }
        });

        series3.setSpacing(10);

// draw values on top
        series3.setDrawValuesOnTop(true);
        series3.setValuesOnTopColor(Color.RED);

//series.setValuesOnTopSize(50);

    return view;
    }


/*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartQ1 = (BarChart) view.findViewById(R.id.chartQ1);

        chartQ2 = (HorizontalBarChart) view.findViewById(R.id.chartQ2);

        mockResultSurveyDemo();
    }

    /**
     * For demo only

    private void mockResultSurveyDemo() {

        ArrayList<Integer> colors = new ArrayList<Integer>();

//Résultat pour les 3 questions du sondage.
        ArrayList<Integer> nbVoteForQuestion1 = new ArrayList<Integer>();
        nbVoteForQuestion1.addAll(Arrays.asList(2, 5, 3));

        ArrayList<Integer> nbVoteForQuestion2 = new ArrayList<Integer>();
        nbVoteForQuestion2.addAll(Arrays.asList(1, 3, 6));

        int[] nbVoteForQuestion3 = {8, 0, 2};

        BarData barData = new BarData();
//La fonction de récupération des pourcentage est à créer

        List<BarEntry> entries = new ArrayList<BarEntry>();


        colors.add(R.color.unsatisfied);
        colors.add(R.color.neutral);
        colors.add(R.color.satisfied);

        barData = createBarChartObject(nbVoteForQuestion1, colors);
        chartQ1.setData(barData);

        barData = createBarChartObjectStacked(nbVoteForQuestion2, colors);
        chartQ2.setData(barData);

    }

//Prepare date for classic BarChart
    private BarData createBarChartObject(ArrayList<Integer> values, ArrayList<Integer> colors) {

        List<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < values.size(); i++) {
            entries.add(new BarEntry(((float) i), (float) values.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "");

        for (int i = 0; i < colors.size(); i++) {
            dataSet.addColor(colors.get(i));
        }

        BarData barData = new BarData(dataSet);

        return barData;
    }

//For Stacked BarChart
    private BarData createBarChartObjectStacked(ArrayList<Integer> values, ArrayList<Integer> colors) {

        List<BarEntry> entries = new ArrayList<BarEntry>();

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

    public float[] convertIntToFloat(List<Integer> arr) {
        float[] arrResult = new float[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            arrResult[i] = arr.get(i);
        }
        return arrResult;
    }
*/


// TODO Import des Results
// TODO Extraction vote user
// TODO Calcul pourcentage réponse pour chaque question
// TODO Affichage icone choix user et camembert
}
