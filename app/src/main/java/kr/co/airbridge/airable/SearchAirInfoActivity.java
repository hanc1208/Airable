package kr.co.airbridge.airable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.Serializable;

import kr.co.airbridge.airable.utility.ActivityUtility;

public class SearchAirInfoActivity extends AppCompatActivity {

    SharedPreferences pref;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"출발편","도착편"};
    int Numboftabs =2;
    String username;

    private DepartureDateDialog dCustomDialog;
    private DepartureCityDialog cCustomDialog;
    private DepartureAirportDialog aCustomDialog;
    private ArrivalDateDialog dACustomDialog;
    private ArrivalCityDialog cACustomDialog;
    private ArrivalAirportDialog aACustomDialog;


    int departure_city=0;
    int arrival_city=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_air_info);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.search_air_info_toolbar);
        activityUtility.setNavigationAsBack();

        Toolbar toolbar  = (Toolbar)findViewById(R.id.search_air_info_toolbar);
        toolbar.setNavigationIcon(R.drawable.back_key);
        toolbar.setTitleTextColor(0xff40C4FF);
        toolbar.setBackgroundColor(0x00000000);

        pref = getSharedPreferences("airable", MODE_PRIVATE);
        username = pref.getString("username", "noname");

        //RelativeLayout ll = (RelativeLayout)findViewById(R.id.activity_search_air_info);
        //ll.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


    }

    public void onClickView(View v) {



        switch (v.getId()) {


            case R.id.departure_airport_textview:
            case R.id.departure_tab_airport_search:
                aCustomDialog = new DepartureAirportDialog(this, leftListener3,itemClickListener3);
                aCustomDialog.show();
                break;
            case R.id.arrival_airport_textview:
            case R.id.arrival_tab_airport_search:
                aACustomDialog = new ArrivalAirportDialog(this, AleftListener3,AitemClickListener3);
                aACustomDialog.show();
                break;
            case R.id.departure_city_textview:
            case R.id.departure_tab_city_search:
                cCustomDialog = new DepartureCityDialog(this, leftListener2,itemClickListener2);
                cCustomDialog.show();
                break;
            case R.id.arrival_city_textview:
            case R.id.arrival_tab_city_search:
                cACustomDialog = new ArrivalCityDialog(this, AleftListener2,AitemClickListener2);
                cACustomDialog.show();
                break;
            case R.id.departure_date_textview:
            case R.id.departure_tab_date_search:
                dCustomDialog = new DepartureDateDialog(this, leftListener,itemClickListener);
                dCustomDialog.show();
                break;
            case R.id.arrival_date_textview:
            case R.id.arrival_tab_date_search:
                dACustomDialog = new ArrivalDateDialog(this, AleftListener,AitemClickListener);
                dACustomDialog.show();
                break;
            case R.id.departure_search_btn:
                if(departure_city==1){
                    OnDepartureSearchClick();
                }
                break;
            case R.id.arrival_search_btn:
                if(arrival_city==1) {
                    OnArrivalSearchClick();
                }
                break;

        }
    }


    public void OnDepartureSearchClick() {


        ImageButton departure_btn = (ImageButton)findViewById(R.id.departure_search_btn);

        if(departure_btn.isClickable()){

            Intent intent = new Intent(getApplicationContext(),
                    AirInfoListActivity.class
            );

            TextView date = (TextView) findViewById(R.id.departure_date_textview);
            TextView city = (TextView) findViewById(R.id.departure_city_textview);

            DepartureSelect info = new DepartureSelect();
            info.s_date = date.getText().toString();
            info.s_city = city.getText().toString();
            intent.putExtra("departure", info);
            intent.putExtra("requestCode", 0);
            startActivity(intent);

        }


    }

    public void OnArrivalSearchClick() {

        ImageButton arrival_btn = (ImageButton)findViewById(R.id.arrival_search_btn);

        if(arrival_btn.isClickable()) {
            Intent intent = new Intent(getApplicationContext(),
                    AirInfoListActivity.class
            );

            TextView date = (TextView) findViewById(R.id.arrival_date_textview);
            TextView city = (TextView) findViewById(R.id.arrival_city_textview);

            ArrivalSelect info = new ArrivalSelect();
            info.s_date = date.getText().toString();
            info.s_city = city.getText().toString();
            intent.putExtra("arrival", info);
            intent.putExtra("requestCode", 1);
            startActivity(intent);
        }

    }

    private View.OnClickListener AleftListener = new View.OnClickListener() {
        public void onClick(View v) {
            dACustomDialog.dismiss();
        }
    };
    private View.OnClickListener AleftListener2 = new View.OnClickListener() {
        public void onClick(View v) {
            cACustomDialog.dismiss();
        }
    };
    private View.OnClickListener AleftListener3 = new View.OnClickListener() {
        public void onClick(View v) {
            aACustomDialog.dismiss();
        }
    };

    private View.OnClickListener leftListener = new View.OnClickListener() {
        public void onClick(View v) {
            dCustomDialog.dismiss();
        }
    };
    private View.OnClickListener leftListener2 = new View.OnClickListener() {
        public void onClick(View v) {
            cCustomDialog.dismiss();
        }
    };
    private View.OnClickListener leftListener3 = new View.OnClickListener() {
        public void onClick(View v) {
            aCustomDialog.dismiss();
        }
    };


    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tv = (String)parent.getAdapter().getItem(position);
            ((TextView)findViewById(R.id.departure_date_textview)).setText(tv);
            dCustomDialog.dismiss();

        }
    };
    private AdapterView.OnItemClickListener itemClickListener2 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ImageButton departure_btn = (ImageButton)findViewById(R.id.departure_search_btn);
            departure_btn.setEnabled(true);
            departure_btn.setClickable(true);
            departure_btn.setImageResource(R.drawable.search_fill);

            departure_city=1;
            String tv = (String)parent.getAdapter().getItem(position);
            ((TextView)findViewById(R.id.departure_city_textview)).setText(tv);
            cCustomDialog.dismiss();

        }
    };
    private AdapterView.OnItemClickListener itemClickListener3 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tv = (String)parent.getAdapter().getItem(position);
            ((TextView)findViewById(R.id.departure_airport_textview)).setText(tv);
            aCustomDialog.dismiss();

        }
    };
    private AdapterView.OnItemClickListener AitemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tv = (String)parent.getAdapter().getItem(position);
            ((TextView)findViewById(R.id.arrival_date_textview)).setText(tv);
            dACustomDialog.dismiss();

        }
    };
    private AdapterView.OnItemClickListener AitemClickListener2 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ImageButton arrival_btn = (ImageButton) findViewById(R.id.arrival_search_btn);
            arrival_btn.setEnabled(true);
            arrival_btn.setClickable(true);
            arrival_btn.setImageResource(R.drawable.search_fill);

            arrival_city=1;

            String tv = (String)parent.getAdapter().getItem(position);
            ((TextView)findViewById(R.id.arrival_city_textview)).setText(tv);
            cACustomDialog.dismiss();

        }
    };
    private AdapterView.OnItemClickListener AitemClickListener3 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tv = (String)parent.getAdapter().getItem(position);
            ((TextView)findViewById(R.id.arrival_airport_textview)).setText(tv);
            aACustomDialog.dismiss();

        }
    };

}

class DepartureSelect implements Serializable {
    String s_date;
    String s_city;
}

class ArrivalSelect implements Serializable {
    String s_date;
    String s_city;
}


