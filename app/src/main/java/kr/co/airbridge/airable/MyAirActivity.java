package kr.co.airbridge.airable;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyAirActivity extends AppCompatActivity {
    private SharedPreferences pref;
    LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_air);


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strNow = sdfNow.format(date);

        pref = getSharedPreferences("airable", MODE_PRIVATE);
        String flight_name = pref.getString("flight_name","");
        String checkin = pref.getString("checkin","");
        String airline = pref.getString("airline","");
        String gate = pref.getString("gate","");
        String airport = pref.getString("airport","");
        String airport_code = pref.getString("airport_code","");
        String datetime = pref.getString("date","");

        TextView arrival_airport_code = (TextView)findViewById(R.id.my_air_info_arrival_airport);
        TextView arrival_airport = (TextView)findViewById(R.id.my_air_info_arrival_airport_text);
        TextView datedate = (TextView)findViewById(R.id.my_air_info_date_time_text);
        TextView ticket_num = (TextView)findViewById(R.id.my_air_info_ticket_num_text);
        TextView check = (TextView)findViewById(R.id.my_air_info_checkin_text);
        TextView gatenum = (TextView)findViewById(R.id.my_air_info_gate_text);
        TextView airlinetext = (TextView)findViewById(R.id.my_air_info_airline_text);

        arrival_airport_code.setText(airport_code);
        arrival_airport.setText(airport);
        datedate.setText(datetime);
        ticket_num.setText(flight_name);
        check.setText(checkin);
        gatenum.setText(gate);
        airlinetext.setText(airline);


        TextView textView = (TextView)findViewById(R.id.my_air_info_update_time_textview);

        textView.setText(strNow);

        TabHost tabHost = (TabHost) findViewById(R.id.my_air_info_tabHost);
        mLocalActivityManager = new LocalActivityManager(this, false);

        tabHost.setup(mLocalActivityManager);
        mLocalActivityManager.dispatchCreate(savedInstanceState);


        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, MyInfoDepartureTabActivity.class);
        spec = tabHost.newTabSpec("departureTab").setIndicator("", getResources().getDrawable(R.drawable.my_air_info_departure_tab_drawable)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MyInfoArrivalTabActivity.class);
        spec = tabHost.newTabSpec("arrivalTab").setIndicator("",getResources().getDrawable(R.drawable.my_air_info_arrival_tab_drawable)).setContent(intent);
        tabHost.addTab(spec);



        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
        {
            tabHost.getTabWidget().getChildAt(i).setPadding(0,0,0,0);
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(0x00000000);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocalActivityManager.dispatchPause(isFinishing());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocalActivityManager.dispatchResume();
    }


    public void onclick(View view){

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strNow = sdfNow.format(date);



        TextView textView = (TextView)findViewById(R.id.my_air_info_update_time_textview);

        textView.setText(strNow);

    }

    public void FlightClick(View view){

        finish();
    }
}
