package nantes_sqli.rhchain;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, viewGroup,false);
    }

//TODO     Import des Results
//TODO     Extraction vote user
//    TODO Calcul pourcentage réponse pour chaque question
//    TODO Affichage icone choix user et camembert
}
