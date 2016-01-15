package kr.co.airbridge.airable;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MyInfoDepartureTabActivity extends Activity{
    private SharedPreferences pref;
    RetrofitServer retrofitServer = new RetrofitServer(1);

    private ListView m_ListView;
    private MyAirInfoDepartureListAdapter  m_Adapter;
    private ArrayList<MyAirListDepartureFlight> m_List;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_air_info_listview);


        m_List = new ArrayList<>();

        // 커스텀 어댑터 생성
        m_Adapter = new MyAirInfoDepartureListAdapter(m_List);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.my_air_info_departure_tab_list);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);



        pref = getSharedPreferences("airable", MODE_PRIVATE);
        String airport_code = pref.getString("airport_code","");

        retrofitServer.postDepartureFlightList(airport_code).enqueue(new Callback<DepartureFlightResponse>() {
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




                    for(DepartureFlight df : response.body().getBody().items) { //// 전체 목록에서 DepartureFlight 오브젝트를 하나씩 뽑아와서 뿌려줌

                        String time = df.getScheduleDataTime();
                        String year = time.substring(2, 4);
                        String month = time.substring(4, 6);
                        String date = time.substring(6, 8);
                        String hour = time.substring(8, 10);
                        String min = time.substring(10, 12);

                        String c_time = df.getScheduleDataTime();
                        String c_year = time.substring(2, 4);
                        String c_month = time.substring(4, 6);
                        String c_date = time.substring(6, 8);
                        String c_hour = time.substring(8, 10);
                        String c_min = time.substring(10,12);


                        MyAirListDepartureFlight temp = new MyAirListDepartureFlight(hour+" : "+min,c_hour+" : "+c_min,df.getAirport(),df.getFlightId(),df.getRemark(),df.getChkinrange(),df.getGatenumber());
                        m_List.add(temp);
                    }
                }catch(Exception e){

                }
                m_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                // resultTextView.setText(t.getMessage()); <------- 에러 메시지 출력
                // Log.i("mytag", "API Failure");
            }
        });
        /*
        MyAirListDepartureFlight Item1 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item2 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item3 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item4 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item5 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item6 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item7 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item8 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item9 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");
        MyAirListDepartureFlight Item10 = new MyAirListDepartureFlight("00:10","","싱가포르","SQ603","마감예정","L01-M12","34");


        m_List.add(Item1);
        m_List.add(Item2);
        m_List.add(Item3);
        m_List.add(Item4);
        m_List.add(Item5);
        m_List.add(Item6);
        m_List.add(Item7);
        m_List.add(Item8);
        m_List.add(Item9);
        m_List.add(Item10);*/

    }
}

