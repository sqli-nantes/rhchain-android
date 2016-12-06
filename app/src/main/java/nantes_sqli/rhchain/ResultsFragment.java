package nantes_sqli.rhchain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultsFragment extends Fragment {
    public ResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, viewGroup, false);
        return view;
    }

// TODO Import des Results
// TODO Extraction vote user
// TODO Calcul pourcentage réponse pour chaque question
// TODO Affichage icone choix user et camembert
}
