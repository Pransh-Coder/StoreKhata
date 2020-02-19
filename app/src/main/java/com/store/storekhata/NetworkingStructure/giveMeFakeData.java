package com.store.storekhata.NetworkingStructure;

import com.store.storekhata.TrackDebit.Debt_Pojo;

import java.util.ArrayList;

public class giveMeFakeData {

    static ArrayList<Debt_Pojo> giveMeFakeData(){
        ArrayList<Debt_Pojo> debt_pojosList= new ArrayList<>();
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","1","pransh","200"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","2","pranshi","160"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","3","Saurabh","10"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","4","sourav","420"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","5","vivek","20"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","3","Saurabh","120"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","6","AAkash","10"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","1","pransh","120"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","7","Saloni","90"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","4","sourav","120"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","7","ram","20"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","8","shyam","97"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","9","Gahnshyam","30"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","8","shyam","25"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","1","pransh","120"));
        debt_pojosList.add(new Debt_Pojo("1","abc","1","2","8","shyam","1020"));

        return debt_pojosList;
    }
}
