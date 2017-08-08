package zechat.android.training.zemoso.zechat.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zechat.android.training.zemoso.zechat.Model.ContactInfo;
import zechat.android.training.zemoso.zechat.R;



public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    public interface AdapterInterface {
        public void sendContact(ContactInfo contact);
    }

    //region Variables
    AdapterInterface adapterInterface;
    private List<ContactInfo> contactList;
    Toolbar toolbar;
    Menu menu;
    RecyclerView recyclerView;
    private int mSelectedPosition = -1;
    private View mSelectedView;
    ContactInfo contact;
    private ContactsAdapter madapter;

    //endregion

    public ContactsAdapter(Toolbar toolbar, RecyclerView recyclerView, AdapterInterface adapterInterface, ContactsAdapter madapter) {
        this.toolbar = toolbar;
        this.recyclerView = recyclerView;
        this.adapterInterface = adapterInterface;
        this.madapter = madapter;
    }

    // region Inherited Methods
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("position", String.valueOf(mSelectedPosition));
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == mSelectedPosition) {
            holder.itemView.setSelected(true);
            mSelectedView = holder.itemView;
        } else {
            holder.itemView.setSelected(false);
        }
        contact = contactList.get(position);
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

        public MyViewHolder(final View view) {
            super(view);
            contactInfo = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!v.isSelected()) {
                        if (mSelectedView != null) {
                            Log.d("selected", "something is already selected");
                            mSelectedView.setSelected(false);
                            menu.findItem(R.id.action_favorite).setVisible(false);
                        }
                        Log.d("selected", "something new is selected");
                        mSelectedPosition = recyclerView.getChildAdapterPosition(v);
                        v.setSelected(true);
                        adapterInterface.sendContact(contactList.get(mSelectedPosition));
                        mSelectedView = v;
                    } else {
                        Log.d("selected", "deselcting");
                        v.setSelected(false);
                        mSelectedPosition = -1;
                        mSelectedView = null;
                    }

                    menu.findItem(R.id.action_favorite).setVisible(!menu.findItem(R.id.action_favorite).isVisible());
                }
                   /* Intent intent = new Intent(context,EditContact.class);
                    context.startActivity(intent);*/
            });
        }
    }
    //endregion

    //region populating List items in recyclerview
    public void addList(List<ContactInfo> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    //endregion
    //endregion
    }
