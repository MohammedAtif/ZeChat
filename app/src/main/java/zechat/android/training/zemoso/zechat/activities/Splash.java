package zechat.android.training.zemoso.zechat.activities;

import android.content.Intent;
import android.os.Bundle;

import zechat.android.training.zemoso.zechat.R;

public class Splash extends FullScreenActivity {

    //region Inherited Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    //endregion

}

