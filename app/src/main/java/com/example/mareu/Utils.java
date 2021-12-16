package com.example.mareu;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    public static String parseListToString(ArrayList<String> listResult){

        String resultat = "";
        for (int j = 0 ; j<listResult.size() ; listResult.size()) {
            if (j==listResult.size()-1)
                resultat = resultat + listResult.get(j);
            else
                resultat = resultat + listResult.get(j)+", ";
        }
        return resultat;
    }

    public static ArrayList<String> parseStringToList(String result){
        ArrayList resultats = new ArrayList<String>(Arrays.asList(result.split(",")));

        return resultats;
    }

   /* public static ArrayList<String> generateFakeMails(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("fred@gmail.com");
        arrayList.add("farah@yahoo.com");
        arrayList.add("quentin@lazeo.com");
        arrayList.add("emna@hotmail.com");
        arrayList.add("jean@gmail.com");
        arrayList.add("claire@gmail.com");
        return arrayList;
    }*/

}
