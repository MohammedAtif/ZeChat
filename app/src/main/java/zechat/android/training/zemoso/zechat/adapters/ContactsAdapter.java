package zechat.android.training.zemoso.zechat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zechat.android.training.zemoso.zechat.R;
import zechat.android.training.zemoso.zechat.utils.Contact;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
    private List<Contact> contactList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView contactInfo, status;

        public MyViewHolder(View view) {
            super(view);
            contactInfo = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
        }
    }

    public ContactsAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.contactInfo.setText(contact.getContactName());
        holder.status.setText(contact.getStatus());
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
