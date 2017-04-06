package com.vegantravels.model;

import com.google.gson.annotations.SerializedName;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAFI on 4/5/2017.
 */

public class CruiseJson {
    @SerializedName(StaticAccess.KEY_CRUISES_JSON_ARRAY)
    public List<Cruises> mCruiseList = new ArrayList<>();

    public CruiseJson(List<Cruises> mCruiseList)
    {
        this.mCruiseList = mCruiseList;
    }

}
