package zechat.android.training.zemoso.zechat.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import zechat.android.training.zemoso.zechat.java_beans.Startup;

/**
 * Created by zemoso on 25/7/17.
 */

public class DownloadResultReceiver extends ResultReceiver {

    //        private Receiver mReceiver;
    private ArrayList<String> results;

    public DownloadResultReceiver(Handler handler) {
        super(handler);
    }

//        interface Receiver{
//            void onReceiveResult(int resultCode, Bundle resultData);
////        }
//
//        public void setReceiver(Receiver receiver) {
//            mReceiver = receiver;
//        }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        Log.d("Result","Received");
        switch (resultCode) {
            case DownloadDataService.STATUS_RUNNING:
//                    setProgressBarIndeterminateVisibility(true);
                break;
            case DownloadDataService.STATUS_FINISHED:
                results = resultData.getStringArrayList("result");
                Log.d("Results Size", String.valueOf(results.size()));
//                    TODO: Update Views with results.

//                    TODO: Update Realm with results.
                Realm realm = Realm.getDefaultInstance();
                Log.d("Realm",realm.toString());
                realm.beginTransaction();
                int k=0;
                for(String string : results) {
                    Startup startup = realm.createObject(Startup.class,k++);
//                            JSONObject jsonObject = new JSONObject(string);
//                            startup.setJsonObject(jsonObject.toString());
//                            startup.setId((Integer) jsonObject.get("id"));
                    startup.setJsonObject(string);
                    Log.d("Realm","Populating");
                }
                realm.commitTransaction();
                Log.d("Realm","Populated "+realm.where(Startup.class).findAll().size());
                realm.close();
                break;
            case DownloadDataService.STATUS_ERROR:
                Log.d("Realm","Status Errors");
                break;

        }
    }


}
