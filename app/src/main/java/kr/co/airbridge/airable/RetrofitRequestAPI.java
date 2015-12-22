package kr.co.airbridge.airable;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.Call;

// 테스트용 코드
public interface RetrofitRequestAPI {
        @POST("post") // Post call 테스트용 주소
        Call<ArrivalFlight> postArrivalFlight(@Body ArrivalFlight arrivalFlight);
}
