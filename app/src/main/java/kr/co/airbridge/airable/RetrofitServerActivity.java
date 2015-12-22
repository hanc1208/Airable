package kr.co.airbridge.airable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

// 테스트용 코드
public class RetrofitServerActivity extends Activity {
    RetrofitServer<ArrivalFlight> retrofitServer;

    int trial = 0;
    Button srchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search2); -->테스트용이라 Layout 추가 안함

        // Retrofit server 오브젝트 생성
        retrofitServer = new RetrofitServer<ArrivalFlight>();

        // Button onClickListener
        srchBtn = (Button)findViewById(R.id.button);
        srchBtn.setOnClickListener(btnListener);
        srchBtn.setText("Trial : "+trial);
    }

    // Mouse
    Button.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // New ArrivalFlight instance
            ArrivalFlight arrivalFlight = new ArrivalFlight();
            arrivalFlight.setAirline("Test" + trial++);
            arrivalFlight.setAirport("TestAirport");
            arrivalFlight.setCarousel("TestCarousel");
            arrivalFlight.setEstimatedDataTime("TestEDT");
            arrivalFlight.setExitnumber("TestExitnumber");
            arrivalFlight.setFlightId("TestFlightId");
            arrivalFlight.setGatenumber("TestGatenumber");
            arrivalFlight.setRemark("TestRemark");
            arrivalFlight.setScheduleDataTime("TestSDT");

            Log.i("mytag", "New arrivalFlight = " + arrivalFlight.getAirline());

            // Post call
            retrofitServer.POST_ArrivalFlight(arrivalFlight);

            srchBtn.setText("Trial : "+trial);
        }
    };
}
