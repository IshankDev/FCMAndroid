package com.x.notificationtest.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.x.notificationtest.R;
import com.x.notificationtest.presenter.FirstActivityPresenter;
import com.x.notificationtest.utils.Constant;

public class FirstActivity extends AppCompatActivity implements FirstActivityPresenter.View {

    private static final String TAG = "FirstActivity";
    FirstActivityPresenter presenter;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_first);
        setSupportActionBar(toolbar);

        //presenter initializations
        presenter = new FirstActivityPresenter(this);

        //to setup back button in activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        initView(); //basic function to setup view

    }

    private void initView() {
        tvData = findViewById(R.id.tv_data);


        if (getIntent().getExtras() != null) {

            //fetching data in extras
            Bundle b = getIntent().getExtras();
            String someData = b.getString(Constant.KEY_DATA);

            if (someData != null) {

                //calling presenter to update view
                presenter.updateNotiData(someData);
            }
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //to manage back button click
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void updateView(String info) {
        if (info != null) {
            String stringBuilder = "Account Type - " +
                    info;
            tvData.setText(stringBuilder);
        } else {
            //handling if no data
            tvData.setText(R.string.hello_user);
        }
    }
}
