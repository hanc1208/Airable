package kr.co.airbridge.airable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ApiTestActivity extends AppCompatActivity {
    //////////////////////////////////////////////////////////////////필요한 부분 시작//////////////////////////////////////////////////////////////////
    //인자 없이 RetrofitServer를 생성하면 Gson Converter가 적용되므로, 인자에 '1''을 넣어서 SimpleXml Converter가 적용되게 하자!
    RetrofitServer retrofitServer = new RetrofitServer(1); // <--- 1을 넣어주세요
    //////////////////////////////////////////////////////////////////필요한 부분 끝//////////////////////////////////////////////////////////////////

    @Bind(R.id.apitest_send_edittext)
    EditText sendEditText;
    @Bind(R.id.apitest_result_textview)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apitest);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.apitest_send_button)
    public void onApiTestSendClick(){
        String airportCode = sendEditText.getText().toString();
        if("".equals(airportCode)) airportCode = "NRT";
        resultTextView.setText("");

        Log.i("mytag", "Send Button clicked : " + airportCode);
        //////////////////////////////////////////////////////////////////필요한 부분 시작//////////////////////////////////////////////////////////////////
        ////// Response Type은 DepartureFlightResponse, 넣어줄 인자는 String으로 된 airportCode
        /// 근데 사실 이거 onResponse랑 onFailure 안에 있는 내용들만 바꿔주면 되고, 나머지는 그냥 복사 붙여넣기 하면 된당
        retrofitServer.postDepartureFlightList(airportCode).enqueue(new Callback<DepartureFlightResponse>() {
            @Override
            public void onResponse(Response<DepartureFlightResponse> response, Retrofit retrofit) {
                /*
                Json방식과 달리 XML 방식을 컨버팅 하는건 그다지 원하는 결과대로 나오지 않았음
                예를 들면 Header와 body를 내가 직접 잘라줘야 한다든가..

                그래서 새로 만든 Type이 DepartureFlightResponse 클래스.
                response.body()를 하면 저 DepartureFlightResponse 타입의 response에 접근이 가능함. body()꼭 붙여줘야함
                response.body().getHeader() -> 받은 xml에서 header 부분. 내부적으로 getResultCode와 getResultMsg가 있음.
                response.body().getBody() -> 이게 우리가 바라던 body 부분인데, 어차피 내부에 또 list가 있어서 이거 쓸 일은 없음
                response.body().getBody().items -> 이게 진짜 우리가 바라던 바로 결과 목록들. items는 List<DepartureFlight>의 타입을 가지고 있으며,
                                                            물론 하나하나의 요소들은 DepartureFlight 타입으로 되어있음.
                                                            참고로 DepartureFlight의 요소들 중 몇몇개는 비어있을 수 있음
                                                            대표적으로 remark 같은 경우는 출발까지 시간이 오래 남은 항공편의 경우 제공 안됨.
                 */
                try{ // try catch 안하면 뭐라고 시비걸어서 넣기는 넣었는데 왜 넣었는진 잘 모르겠다.. items가 null일 수도 있어서 그런것 같음;; 걍 넣어줭 :D
                    String temp = "";
                    for(DepartureFlight df : response.body().getBody().items) { //// 전체 목록에서 DepartureFlight 오브젝트를 하나씩 뽑아와서 뿌려줌
                        temp+="Airline : "+df.getAirline()+", Airport : "+df.getAirport()+", Scheduled Time : "+df.getScheduleDataTime()+", Remark : "+df.getRemark()+", AirportCode : "+df.getAirportCode()+"\n";
                    }
                    resultTextView.setText(temp);
                }catch(Exception e){

                }
            }

            @Override
            public void onFailure(Throwable t) {
                // resultTextView.setText(t.getMessage()); <------- 에러 메시지 출력
                // Log.i("mytag", "API Failure");
            }
        });
        //////////////////////////////////////////////////////////////////필요한 부분 끝//////////////////////////////////////////////////////////////////
    }

}
