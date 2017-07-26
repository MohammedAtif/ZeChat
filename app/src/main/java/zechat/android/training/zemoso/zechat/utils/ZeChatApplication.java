package zechat.android.training.zemoso.zechat.utils;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import zechat.android.training.zemoso.zechat.services.ASyncDownload;

/**
 * Created by vin on 24/7/17.
 */

public class ZeChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application","Started");
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.deleteRealm(configuration);
        Realm.setDefaultConfiguration(configuration);
//        DownloadResultReceiver mReceiver = new DownloadResultReceiver(new Handler());
        String url = "http://192.168.86.75:3000/chats";
//        Intent intent = new Intent(getApplicationContext(), DownloadDataService.class);
//        intent.putExtra("url", url);
//        Log.d(intent.toString(),url);
//        intent.putExtra("receiver", mReceiver);
//        startService(intent);
        new ASyncDownload().execute(url);
    }



}
