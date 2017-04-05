package com.vegantravels.asynctask;

import android.os.AsyncTask;

/**
 * Created by Rakib on 4/5/2017.
 */

public abstract class FrameWorkAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        prepareHeavyWork();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        doHeavyWork();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        heavyWorkResult();
    }


    abstract void prepareHeavyWork();

    abstract void doHeavyWork();

    abstract void heavyWorkResult();
}
