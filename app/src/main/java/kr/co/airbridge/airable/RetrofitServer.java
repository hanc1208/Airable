package kr.co.airbridge.airable;

import android.util.Log;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


// 테스트용 코드
public class RetrofitServer {
    Retrofit retrofit = null;
    RetrofitRequestAPI retrofitRequestAPI;

    public RetrofitServer() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://125.143.162.222/airable/")
                    .addConverterFactory(GsonConverterFactory.create()) // Gson (JSON -> JAVA)
                    .build();
            Log.i("mytag", "Retrofit created with gson");

            retrofitRequestAPI = retrofit.create(RetrofitRequestAPI.class);
        }
    }

    public RetrofitServer(int type) {
        if (retrofit == null) {
            if (type == 1) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://125.143.162.222/airable/")
                        .addConverterFactory(SimpleXmlConverterFactory.create()) // SimpleXml (XML -> JAVA)
                        .build();
                Log.i("mytag", "Retrofit created with simplexml");
            }
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://125.143.162.222/airable/")
                    .addConverterFactory(GsonConverterFactory.create()) // Gson (JSON -> JAVA)
                    .build();
            Log.i("mytag", "Retrofit created with gson");
        }
        retrofitRequestAPI = retrofit.create(RetrofitRequestAPI.class);
    }


    // 사용자들이 사용하게 될 함수들
    // public Call<통신 성공 시 받을 Response의 Type> 함수 이름 (전달하고 싶은 오브젝트) {...}
    public Call<ArrivalFlight> postArrivalFlight(ArrivalFlight arrivalFlight) {
        return retrofitRequestAPI.API_PostArrivalFlight(arrivalFlight);
    }

    public Call<String> postLogin(String email, String pw) {
        return retrofitRequestAPI.API_PostLogin(email, pw);
    }

    public Call<String> postRegister(String name, String email, String pw) {
        return retrofitRequestAPI.API_PostRegister(name, email, pw);
    }

    public Call<DepartureFlightResponse> postDepartureFlightList(String airportCode) {
        return retrofitRequestAPI.API_PostDepatureFlightList(airportCode);
    }
}

// Retrofit을 이용한 Request를 위한 API. 사용자들은 이 곳이 아닌 위에 있는 public method들을 이용할 것.
interface RetrofitRequestAPI {
    @POST("post")
        // Post call 테스트용 주소
    Call<ArrivalFlight> API_PostArrivalFlight(@Body ArrivalFlight arrivalFlight);

    @FormUrlEncoded
    @POST("login.php")
    Call<String> API_PostLogin(@Field("email") String email, @Field("pw") String pw);

    @FormUrlEncoded
    @POST("join.php")
    Call<String> API_PostRegister(@Field("name") String name, @Field("email") String email, @Field("pw") String pw);

    @FormUrlEncoded
    @POST("airportapi/departure.php")
    Call<DepartureFlightResponse> API_PostDepatureFlightList(@Field("airport_code") String airportCode);

}
