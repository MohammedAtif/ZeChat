package zechat.android.training.zemoso.zechat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.EditText;

import zechat.android.training.zemoso.zechat.R;

import static android.app.PendingIntent.getActivity;

public class EditContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_edit_contact);
        EditText editContact = (EditText) findViewById(R.id.editContact);
        EditText editStatus = (EditText) findViewById(R.id.editStatus);
        editContact.setText(extras.getString("contact_name"));
        editStatus.setText(extras.getString("contact_status"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }
}