package kr.co.airbridge.airable.utility;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActivityUtility {

    private AppCompatActivity activity;
    private Toolbar toolbar;

    public ActivityUtility(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setToolbar(int id) {
        toolbar = (Toolbar) activity.findViewById(id);
        activity.setSupportActionBar(toolbar);
    }

    public void setNavigationAsBack() {
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }
}
