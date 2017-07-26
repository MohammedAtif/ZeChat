package zechat.android.training.zemoso.zechat.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownloadDataService extends IntentService {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private static final String TAG = DownloadDataService.class.getSimpleName();
    public DownloadDataService() {
        super("DownloadDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"Service Started.");

        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Log.d(TAG,receiver.toString());
        String url = intent.getStringExtra("url");
        Bundle bundle = new Bundle();
        Log.d(TAG,url);

        receiver.send(STATUS_RUNNING,Bundle.EMPTY);
        try{
            ArrayList<String> results = downloadData(url);
            if(results!=null&&results.size()>0){
                bundle.putStringArrayList("result",results);
                receiver.send(STATUS_FINISHED,bundle);
                Log.d("ArrayList",results.toString());
                Log.d("Finished",bundle.toString());
            }
        } catch(Exception e){
            bundle.putString(Intent.EXTRA_TEXT,e.getMessage());
            receiver.send(STATUS_ERROR,bundle);
        }

        Log.d(TAG,"Service Stopped");
        this.stopSelf();
    }

    //region Supporting Private Methods
    private ArrayList<String> downloadData(String requestUrl) throws Exception {
        Log.d("Trying","Download");
        InputStream inputStream;
        HttpURLConnection urlConnection;
        URL url = new URL(requestUrl);
        urlConnection = (HttpURLConnection) url.openConnection();
        Log.d("Connecting...",urlConnection.toString());
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setRequestMethod("GET");
        Log.d("Download","Finished");
        int statusCode = urlConnection.getResponseCode();
        Log.d("Connecting...", String.valueOf(statusCode));
        if(statusCode == 200){
            Log.d("Trying","Parsing");
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String response = convertInputStreamToString(inputStream);
            Log.d(TAG,response);
            return parseResult(response);
        } else {
            throw new Exception("Unable to fetch data");
        }
    }

    private ArrayList<String> parseResult(String response) throws JSONException {
        ArrayList<String> items = new ArrayList<>();
        JSONArray posts = new JSONArray(response);
        Log.d(TAG,posts.toString());

        for(int i=0;i<posts.length();i++){
            JSONObject post = posts.optJSONObject(i);
            items.add(post.toString());
            Log.d("JSON",post.toString());
        }

        return items;

    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line=bufferedReader.readLine())!=null)
            result+=line;
        inputStream.close();
        return result;
    }
    //endregion


}
