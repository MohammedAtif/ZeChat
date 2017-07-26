package zechat.android.training.zemoso.zechat.services;

import android.os.AsyncTask;
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

import io.realm.Realm;
import zechat.android.training.zemoso.zechat.java_beans.Startup;

/**
 * Created by zemoso on 26/7/17.
 */

public class ASyncDownload extends AsyncTask<String,Void,Integer> {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    private static final String TAG = ASyncDownload.class.getSimpleName();
    private ArrayList<String> results;
    @Override
    protected Integer doInBackground(String... strings) {

        try{
            String requestUrl = strings[0];
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
                results = parseResult(response);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer==1){
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
                Log.d(startup.getJsonObject(),"JSON");
            }
            realm.commitTransaction();
            Log.d("Realm","Populated "+realm.where(Startup.class).findAll().size());
            realm.close();
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
}
