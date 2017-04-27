package com.sqli.blockchain.rhchain;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import ethereumjava.solidity.types.SArray;
import ethereumjava.solidity.types.SInt;

/**
 * Created by gunicolas on 24/04/17.
 */

public abstract class Utils {


    public static boolean isAddressValid(String email){
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public static void showAlertDialog(Context context,String message){
        new AlertDialog.Builder(context)
                .setTitle("Attention")
                .setMessage(message)
                .setNeutralButton("OK",(dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static int[][] sarrayToList(SArray<SArray<SInt.SInt256>> sarray){

        List<int[]> listOfArrays = new ArrayList<>();

        for(SArray<SInt.SInt256> row : sarray.get()){
           SInt.SInt256[] rowArray = row.get();
            int rowLength = rowArray.length;
            int[] rowRet = new int[rowLength];
            for(int i=0;i<rowLength;i++){
                rowRet[i] = ((BigInteger) rowArray[i].get()).intValue();
            }
            listOfArrays.add(rowRet);
        }

        return (int[][]) listOfArrays.toArray();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getCurrentLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }



}
