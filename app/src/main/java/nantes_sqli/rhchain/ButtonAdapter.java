package nantes_sqli.rhchain;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;
import java.util.Vector;

/**
 * Created by alb on 21/11/16.
 * Classe pour gérer les changements de bouton lorsque l'utilisateur les sélectionnent
 *
 */
public class ButtonAdapter extends BaseAdapter{
//

   private SurveyActivity mSurveyActivity;
//
//    public ButtonAdapter(SurveyActivity) {
//             mSurveyActivity = testSurveyActivity;
//    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = new Button(mSurveyActivity);
            button.setLayoutParams(new GridView.LayoutParams(30,30));
            button.setPadding(8,8,8,8);
        } else {
            button = (Button) convertView;
        }
//  TODO paramétré les boutons en fonction de la question
//    button.setLayoutParams(new ViewGroup.LayoutParams(getItemId());
        return button;
    }

    private int[] mButtonNeutral = {
//            R.drawable.ic_dissatisfied_neutral, R.drawable.ic_neutral_neutral, R.drawable.ic_satified_neutral
    };

    public static class ResultsFragment extends FragmentActivity {
        private PagerAdapter mPageAdapter;

           private OnFragmentInteractionListener mListener;

    //    public ResultsFragment() {
    //        // Required empty public constructor
    //    }
    //
    //    /**
    //     * Use this factory method to create a new instance of
    //     * this fragment using the provided parameters.
    //     *
    //     * @param param1 Parameter 1.
    //     * @param param2 Parameter 2.
    //     * @return A new instance of fragment ResultsFragment.
    //     */
    //    // TODO: Rename and change types and number of parameters
    //    public static ResultsFragment newInstance(String param1, String param2) {
    //        ResultsFragment fragment = new ResultsFragment();
    //        Bundle args = new Bundle();
    //        args.putString(ARG_PARAM1, param1);
    //        args.putString(ARG_PARAM2, param2);
    //        fragment.setArguments(args);
    //        return fragment;
    //    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            super.setContentView(R.layout.activity_survey);

            List fragments = new Vector();

            fragments.add(Fragment.instantiate(this,ResultsFragment.class.getName()));
            fragments.add(Fragment.instantiate(this, SurveyActivity.class.getName()));
        }

//        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_results, container, false);
        }

        // TODO: Rename method, update argument and hook method into UI event
        public void onButtonPressed(Uri uri) {
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }

//        @Override
//        public void onAttach(Context context) {
//            super.onAttach(context);
//            if (context instanceof OnFragmentInteractionListener) {
//                mListener = (OnFragmentInteractionListener) context;
//            } else {
//                throw new RuntimeException(context.toString()
//                        + " must implement OnFragmentInteractionListener");
//            }
//        }
//
//        @Override
//        public void onDetach() {
//            super.onDetach();
//            mListener = null;
//        }
//
//        /**
//         * This interface must be implemented by activities that contain this
//         * fragment to allow an interaction in this fragment to be communicated
//         * to the activity and potentially other fragments contained in that
//         * activity.
//         * <p>
//         * See the Android Training lesson <a href=
//         * "http://developer.android.com/training/basics/fragments/communicating.html"
//         * >Communicating with Other Fragments</a> for more information.
//         */
        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }
    }
}
