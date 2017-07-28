package zechat.android.training.zemoso.zechat.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zechat.android.training.zemoso.zechat.R;
import zechat.android.training.zemoso.zechat.adapters.ContactsAdapter;
import zechat.android.training.zemoso.zechat.utils.Contact;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {

    private Context mContext;
    private List<Contact> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;

    public Contacts() {
        // Required empty public constructor
    }

    public static Contacts newInstance() {
        Bundle args = new Bundle();
        //TODO: Set your variable values here
        Contacts fragment = new Contacts();
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
        if (arguments != null) {

            //TODO initialize your variables here
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_layout, container, false);
        mAdapter = new ContactsAdapter(contactList);
        recyclerView = view.findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        getContacts();
        return view;

    }

    private void getContacts() {

        Contact contact = new Contact("Raj", "Coding");
        contactList.add(contact);

        contact = new Contact("Akhil", "Sleeping");
        contactList.add(contact);

        contact = new Contact("Rohith", "Working");
        contactList.add(contact);

        contact = new Contact("Varma", "Busy");
        contactList.add(contact);

        contact = new Contact("Saikat", "******akjsdhasukdhasudhiasjkdbaiuksjdkbhkabjskchkajsckhjkhsabakjbcjkja");
        contactList.add(contact);

        contact = new Contact("Manikanta", "Always working");
        contactList.add(contact);

        contact = new Contact("Pranooy", "I'm not funny");
        contactList.add(contact);

        contact = new Contact("Dharma", "I will do PHD in DP");
        contactList.add(contact);

    }
}
