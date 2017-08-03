package zechat.android.training.zemoso.zechat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zechat.android.training.zemoso.zechat.Model.ContactInfo;
import zechat.android.training.zemoso.zechat.R;
import zechat.android.training.zemoso.zechat.activities.MainActivity;
import zechat.android.training.zemoso.zechat.fragments.Contacts;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    //region Variables
    private List<ContactInfo> contactList;
    Context context;

    //endregion

    // region Inherited Methods
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        context = holder.contactInfo.getContext();
        ContactInfo contact = contactList.get(position);
        holder.contactInfo.setText(contact.getName());
        holder.status.setText(contact.getStatus());
    }

    @Override
    public int getItemCount() {
        if (contactList == null) {
            return 0;
        }
        return contactList.size();
    }
    //endregion

    // region public methods
    // region ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView contactInfo, status;

        public MyViewHolder(View view) {
            super(view);
            contactInfo = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(context, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }
    //endregion

    //region populating List items in recyclerview
    public void addList(List<ContactInfo> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }
    //endregion
    //endregion

}
