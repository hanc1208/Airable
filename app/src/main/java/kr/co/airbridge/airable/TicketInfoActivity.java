package kr.co.airbridge.airable;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TicketInfoActivity extends AppCompatActivity {

    RetrofitServer retrofitServer = new RetrofitServer(1);

    String departure_hour ="";
    String departure_min ="";
    String flightId="";


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

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

        Intent intent = getIntent();


        pref = getSharedPreferences("airable", MODE_PRIVATE);
        editor = pref.edit();



        TextView ticket_num = (TextView)findViewById(R.id.ticket_info_ticket_number);
        ticket_num.setText(intent.getStringExtra("ticket_number"));
        flightId=intent.getStringExtra("ticket_number");


        TextView arrival_city = (TextView)findViewById(R.id.ticket_info_arrival_city);
        arrival_city.setText(intent.getStringExtra("arrival_city"));

        TextView arrival_city_ko = (TextView)findViewById(R.id.ticket_info_arrival_city_ko);
        arrival_city_ko.setText(intent.getStringExtra("arrival_city_ko"));

        Log.i("date",intent.getStringExtra("departure_date"));
        String year = intent.getStringExtra("departure_date").substring(2,4);
        String month = intent.getStringExtra("departure_date").substring(4,6);
        String date = intent.getStringExtra("departure_date").substring(6,8);
        departure_hour = intent.getStringExtra("departure_date").substring(8,10);
        departure_min = intent.getStringExtra("departure_date").substring(10, 12);
        String AMPM ="";
        if(Integer.parseInt(departure_hour)<12){
            AMPM = "AM";
        }else{
            AMPM="PM";
        }
        TextView departure_date = (TextView)findViewById(R.id.ticket_info_departure_date2);
        departure_date.setText(year+"."+month+"."+date);

        TextView departure_time = (TextView)findViewById(R.id.ticket_info_departure_time2);
        departure_time.setText(departure_hour+" : "+departure_min);

        TextView departure_time2 = (TextView)findViewById(R.id.ticket_info_departure_time3);
        departure_time2.setText(AMPM);

        TextView departure_check = (TextView)findViewById(R.id.ticket_info_check_in2);
        departure_check.setText(intent.getStringExtra("departure_check"));

        TextView departure_gate = (TextView)findViewById(R.id.ticket_info_gate2);
        departure_gate.setText(intent.getStringExtra("departure_gate"));


        editor.putString("date","20"+year+"."+month+"."+date+" "+departure_hour+":"+departure_min);
        editor.putString("flight_name",intent.getStringExtra("ticket_number"));
        editor.putString("checkin", intent.getStringExtra("departure_check"));
        editor.putString("airline",intent.getStringExtra("arrival_city_ko"));
        editor.putString("gate", intent.getStringExtra("departure_gate"));
        editor.putString("airport", intent.getStringExtra("departure_airline_name"));
        editor.putString("airport_code", intent.getStringExtra("arrival_city"));
        editor.apply();


    }



    @OnClick(R.id.ticket_info_start)
    public void onInfoStartClick() {


        Intent questionActivityIntent = new Intent(getApplicationContext(), QuestionActivity.class);

        questionActivityIntent.putExtra("flightId",flightId);
        questionActivityIntent.putExtra("departure_hour",departure_hour);
        questionActivityIntent.putExtra("departure_min",departure_min);

        Log.i("flight", flightId);
        Log.i("departure_hour",departure_hour);
        Log.i("departure_min",departure_min);
        startActivity(questionActivityIntent);
    }
}