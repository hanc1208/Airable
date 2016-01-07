package kr.co.airbridge.airable;

import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.POST;


// 테스트용 코드
public class RetrofitServer<T> {
    Retrofit retrofit = null;
    RetrofitRequestAPI retrofitRequestAPI;

    public RetrofitServer() {
        // Retrofit을 생성하여 baseUrl과 conterting factory 적용 (사용한 converting 툴은 GSON)
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://httpbin.org/") // Post 함수를 테스트해 볼 수 있는 사이트
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.i("mytag", "Retrofit created");

            retrofitRequestAPI = retrofit.create(RetrofitRequestAPI.class);
        }
    }

    // POST 방식으로 통신할 때 사용되는 method
    public Call<ArrivalFlight> postArrivalFlight (ArrivalFlight arrivalFlight){ return retrofitRequestAPI.API_PostArrivalFlight(arrivalFlight); }
}

// 테스트용 코드
interface RetrofitRequestAPI {
    @POST("post") // Post call 테스트용 주소
    Call<ArrivalFlight> API_PostArrivalFlight(@Body ArrivalFlight arrivalFlight);
}
