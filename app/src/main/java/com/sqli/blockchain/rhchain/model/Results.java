package com.sqli.blockchain.rhchain.model;

/**
 * Created by gunicolas on 20/04/17.
 */

public class Results {

    private int[][] rawResults;
    private int nbAnswers;


    public Results(int[][] rawResults) {
        this.rawResults = rawResults;
        for(int i=0;i<rawResults[0].length;i++){
            nbAnswers += rawResults[0][i];
        }
    }

    public int getNbAnswers(){
        return nbAnswers;
    }

    public int[] getResultsAt(int position){
        return rawResults[position];
    }


}
