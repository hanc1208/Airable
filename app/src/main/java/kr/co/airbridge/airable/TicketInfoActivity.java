package kr.co.airbridge.airable;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class TicketInfoActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_info_form);

        ActivityUtility activityUtility = new ActivityUtility(this);

        activityUtility.setToolbar(R.id.ticket_info_toolbar);
        activityUtility.setNavigationAsBack();
        ButterKnife.bind(this);

        Toolbar toolbar  = (Toolbar)findViewById(R.id.ticket_info_toolbar);
        toolbar.setNavigationIcon(R.drawable.back_key_white);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        getSupportActionBar().setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        getSupportActionBar().setTitle("");


    }



    @OnClick(R.id.ticket_info_start)
    public void onInfoStartClick() {
        Intent questionActivityIntent = new Intent(getApplicationContext(), QuestionActivity.class);
        //편명, 시간정보 보내기
        startActivity(questionActivityIntent);
    }
}