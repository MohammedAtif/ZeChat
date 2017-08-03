package zechat.android.training.zemoso.zechat.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;
import zechat.android.training.zemoso.zechat.Model.ContactInfo;
import zechat.android.training.zemoso.zechat.R;
import zechat.android.training.zemoso.zechat.adapters.ContactsAdapter;
import zechat.android.training.zemoso.zechat.utils.ZeChatApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {

    //region variables
    private Context mContext;
    private List<ContactInfo> contactList;
    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;
    Realm bgRealm;
    final LinearLayoutManager layoutManager;
    ContactInfo contact;
    public static final String TAG = Contacts.class.getSimpleName();
    //endregion

    //region Public Constructor
    public Contacts() {
        layoutManager = new LinearLayoutManager(getContext());
        // Required empty public constructor
    }
    //endregion

    // region Inherited Methods
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getTimer().schedule(task, 0, 3000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ContactsAdapter();
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "resumed");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroyed");
    }
    //endregion

    //region Handler and Timer to download data periodically
    final Handler handler = new Handler();
    Timer timer = new Timer();

    public Timer getTimer() {
        return timer;
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    downloadData();
                }
            });
        }
    };
    //endregion

    //region public methods
    public static Contacts newInstance() {
        Bundle args = new Bundle();
        //TODO: Set your variable values here
        Contacts fragment = new Contacts();
        fragment.setArguments(args);
        return fragment;
    }

    public void downloadData() {
        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";
        //String url = "http://10.0.2.2:3000/contact";
        String url = "http://10.0.2.2:3000/contact";
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        bgRealm = Realm.getDefaultInstance();
                        bgRealm.beginTransaction();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                try {
                                    contact = new ContactInfo();
                                    contact.setName(jsonObject.getString("name"));
                                    contact.setStatus(jsonObject.getString("status"));
                                    contact.setId(jsonObject.getString("id"));
                                    bgRealm.insertOrUpdate(contact);
                                    Log.d("splash", bgRealm.where(ContactInfo.class).findAll().toString());
                                } catch (Exception e) {
                                    bgRealm.cancelTransaction();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        bgRealm.commitTransaction();
                        RealmResults<ContactInfo> entries = bgRealm.where(ContactInfo.class).findAll();
                        contactList = new ArrayList<>(entries);
                        mAdapter.addList(contactList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        ZeChatApplication.getInstance().addToRequestQueue(req, tag_json_arry);
    }
//endregion

    // region HTTP request in background
/*
    class DownloadAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(strings[0]);
            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    Log.e("Json", "Fetching");
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                } else {
                    Log.e("Json", "Failedet JSON object");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Json", builder.toString());
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }*/
//endregion
}