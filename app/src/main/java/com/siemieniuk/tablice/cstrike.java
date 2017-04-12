package com.siemieniuk.tablice;

import android.text.Editable;

/**
 * Created by student on 2017-04-12.
 */

public class cstrike {
    int _id;
    String _nazwa;


    public cstrike(){}

    public cstrike(int id,String nazwa){
        this._id = id;
        this._nazwa = nazwa;
    }

    public cstrike(String nazwa) {
        this._nazwa = nazwa;
    }



    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    public String getNazwa(){
        return this._nazwa;
    }

    public void setNazwa(String nazwa){
        this._nazwa = nazwa;
    }
}


