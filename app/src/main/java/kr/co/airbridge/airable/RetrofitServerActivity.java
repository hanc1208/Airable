package kr.co.airbridge.airable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

// 테스트용 코드
public class RetrofitServerActivity extends Activity{
    ///////////////////////////////////////////////////////////////
    RetrofitServer retrofitServer = new RetrofitServer();
    ///////////////////////////////////////////////////////////////
    int trial = 0;
    Button srchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        // Button onClickListener
        srchBtn = (Button)findViewById(R.id.retrofit_test_button);
        srchBtn.setOnClickListener(btnListener);
        srchBtn.setText("Trial : " + trial);
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

            ///////////////////////////////////////////////////////////////
            // Post call
            retrofitServer.postArrivalFlight(arrivalFlight).enqueue(new Callback<ArrivalFlight>(){
                @Override
                public void onResponse(Response<ArrivalFlight> response, Retrofit retrofit){
                    Log.i("mytag", "Response received");
                    Log.i("mytag", response.message());
                    try{
                        Log.i("mytag", response.body().getAirline());
                    }catch(Exception e){
                        Log.i("mytag", e.getMessage());
                    }
                }
                @Override
                public void onFailure(Throwable t){
                    Log.i("mytag", "Transmission failed : " + t.getLocalizedMessage());
                }
            });
            ///////////////////////////////////////////////////////////////

            srchBtn.setText("Trial : " + trial);
        }
    };
    // Response를 받았을 때 처리
}
