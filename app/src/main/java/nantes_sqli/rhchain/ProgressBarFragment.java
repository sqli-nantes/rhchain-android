package nantes_sqli.rhchain;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by alb on 30/11/16.
 */

public class ProgressBarFragment extends DialogFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        final Dialog prog;

        super.onCreate(savedInstanceState);
        setCancelable(false);
        prog = onCreateDialog(savedInstanceState);
        prog.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                prog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        dialog.setTitle(getString(R.string.pleaseWait));
        dialog.setMessage(getString(R.string.writing_BC));
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }
}
/*
public class ProgressBarFragment extends android.support.v4.app.Fragment {
    public ProgressBarFragment() {
    }
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progressbar, viewGroup, false);
        progress = (ProgressBar)view.findViewById(R.id.progressBar);
        progress = new ProgressDialog(savedInstanceState);
    }


    public void open(View view){
        progress.setMessage("Downloading Music :) ");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.show();

        final int totalProgressTime = 100;

        final Thread t = new Thread(){

            @Override
            public void run(){

                int jumpTime = 0;
                while(jumpTime < totalProgressTime){
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            }
        };
        t.start();
//        View view = inflater.inflate(R.layout.fragment_progressbar, viewGroup, false);
//        return view;


    }
}
*/