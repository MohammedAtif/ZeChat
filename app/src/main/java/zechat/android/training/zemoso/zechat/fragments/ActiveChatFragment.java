package zechat.android.training.zemoso.zechat.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import zechat.android.training.zemoso.zechat.R;
import zechat.android.training.zemoso.zechat.adapters.RecyclerViewAdapter;
import zechat.android.training.zemoso.zechat.interfaces.UpdateRecyclerView;
import zechat.android.training.zemoso.zechat.java_beans.Startup;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveChatFragment extends Fragment implements UpdateRecyclerView {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private List<Startup> mItems;


    public ActiveChatFragment() {
        // Required empty public constructor
    }

    public static ActiveChatFragment newInstance() {
        Bundle args = new Bundle();
        //TODO: Set your variable values here
        ActiveChatFragment fragment = new ActiveChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null){
            //TODO initialize your variables here
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Startup> data = realm.where(Startup.class).findAll();
            mItems = new ArrayList<>(data);
            Log.d("mItems ArrayList", String.valueOf(mItems.size()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_chat_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.chat_recycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext,mItems);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void updateItemList(int position) {
        mItems.remove(position);
        mRecyclerViewAdapter.notifyItemRemoved(position);
    }

}
