package zechat.android.training.zemoso.zechat.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import zechat.android.training.zemoso.zechat.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class ActiveChatFragment extends Fragment {

    //region variables
    private Context mContext;
    //endregion

    //region public constructor
    public ActiveChatFragment() {
        // Required empty public constructor
    }
    //endregion

    //region creating new Instance
    public static ActiveChatFragment newInstance() {
        Bundle args = new Bundle();
        //TODO: Set your variable values here
        ActiveChatFragment fragment = new ActiveChatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //endregion

    //region Inherited Methods
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

            //TODO initialize your variables here
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    //endregion
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
