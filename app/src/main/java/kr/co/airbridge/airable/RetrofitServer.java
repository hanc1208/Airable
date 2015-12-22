package kr.co.airbridge.airable;

import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


// 테스트용 코드
public class RetrofitServer<T> implements Callback<T> {
    Retrofit retrofit = null;
    RetrofitRequestAPI retrofitRequestAPI;
    Call<T> call;

    public RetrofitServer() {
        // Retrofit을 생성하여 baseUrl과 conterting factory 적용 (사용한 converting 툴은 GSON)
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://httpbin.org/") // "ruquestb.in" 은 Post 함수를 테스트해 볼 수 있는 사이트
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.i("mytag", "Retrofit created");

            retrofitRequestAPI = retrofit.create(RetrofitRequestAPI.class);
        }
    }

    // POST 방식으로 통신할 때 사용되는 method
    public void POST_ArrivalFlight (ArrivalFlight arrivalFlight){
        call = (Call<T>)retrofitRequestAPI.postArrivalFlight(arrivalFlight);
        call.enqueue(this);
    }

    // Response를 받았을 때 처리
    @Override
    public void onResponse(Response<T> response, Retrofit retrofit){
        Log.i("mytag", "Response received");
        Log.i("mytag", response.message());
        Log.i("mytag", response.body().toString());
    }

    @Override
    public void onFailure(Throwable t){
        Log.i("mytag", "Transmission failed : "+t.getLocalizedMessage());
    }
}
