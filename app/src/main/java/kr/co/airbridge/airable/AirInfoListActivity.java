package kr.co.airbridge.airable;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kr.co.airbridge.airable.utility.ActivityUtility;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AirInfoListActivity extends AppCompatActivity {


    private ListView    m_ListView;
    private AirInfoListAdapter  m_Adapter;
    private ArrayList<AirListDepartingFlight> m_List;


    RetrofitServer retrofitServer = new RetrofitServer(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_info_list);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.air_info_list_toolbar);
        activityUtility.setNavigationAsBack();


        Toolbar toolbar  = (Toolbar)findViewById(R.id.air_info_list_toolbar);
        toolbar.setNavigationIcon(R.drawable.back_key);
        toolbar.setTitleTextColor(0xff40C4FF);
        toolbar.setBackgroundColor(0x00000000);




        m_List = new ArrayList<>();

        // 커스텀 어댑터 생성
        m_Adapter = new AirInfoListAdapter(m_List);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.air_info_listview);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);



        Intent intent = getIntent();

        int requestCode = (int)intent.getIntExtra("requestCode", -1);

        String airportCode = intent.getStringExtra("departure_airport_code");

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

                    for(DepartureFlight df : response.body().getBody().items) {//// 전체 목록에서 DepartureFlight 오브젝트를 하나씩 뽑아와서 뿌려줌
                        String time = df.getScheduleDataTime();
                        String year = time.substring(2,4);
                        String month = time.substring(4,6);
                        String date = time.substring(6,8);
                        String hour = time.substring(8,10);
                        String min = time.substring(10,12);

                        String c_time = df.getScheduleDataTime();
                        String c_year = time.substring(2, 4);
                        String c_month = time.substring(4, 6);
                        String c_date = time.substring(6, 8);
                        String c_hour = time.substring(8, 10);
                        String c_min = time.substring(10,12);




                        AirListDepartingFlight tempItem = new AirListDepartingFlight(hour+" : "+min,c_hour+" : "+c_min,df.getFlightId(),df.getAirline(),df.getAirport(),df.getChkinrange(),df.getGatenumber(),df.getAirportCode(),df.getScheduleDataTime());
                        m_List.add(tempItem);
                        Log.i("list", df.getAirportCode());
                    }
                    m_Adapter.notifyDataSetChanged();
                }catch(Exception e){

                }
            }

            @Override
            public void onFailure(Throwable t) {
                // resultTextView.setText(t.getMessage()); <------- 에러 메시지 출력
                // Log.i("mytag", "API Failure");
            }
        });

        if (requestCode == 0 ) {
            DepartureSelect d = (DepartureSelect)intent.getSerializableExtra("departure");


            if(!d.s_date.equals("")&&!d.s_city.equals("")){
                getSupportActionBar().setTitle(d.s_date + " / " + d.s_city);
            } else if(d.s_date.equals("")&&!d.s_city.equals("")){
                getSupportActionBar().setTitle(d.s_city);
            }

        } else if(requestCode == 1) {
            ArrivalSelect a = (ArrivalSelect)intent.getSerializableExtra("arrival");


            if(!a.s_date.equals("")&&!a.s_city.equals("")){
                getSupportActionBar().setTitle(a.s_date + " / " + a.s_city);
            } else if(a.s_date.equals("")&&!a.s_city.equals("")){
                getSupportActionBar().setTitle(a.s_city);
            }

        }


        // 아이템을 [클릭]시의 이벤트 리스너를 등록
        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AirInfoListActivity.this, TicketInfoActivity.class);

                intent.putExtra("ticket_number",((AirListDepartingFlight)(parent.getAdapter().getItem(position))).getFlightId());
                intent.putExtra("arrival_city",((AirListDepartingFlight)(parent.getAdapter().getItem(position))).getAirport_code());
                intent.putExtra("arrival_city_ko",((AirListDepartingFlight)(parent.getAdapter().getItem(position))).getAirline());
                intent.putExtra("departure_date",((AirListDepartingFlight)(parent.getAdapter().getItem(position))).getRawdate());
                intent.putExtra("departure_check",((AirListDepartingFlight)(parent.getAdapter().getItem(position))).getChkinrange());
                intent.putExtra("departure_gate",((AirListDepartingFlight)(parent.getAdapter().getItem(position))).getGatenumber());


                startActivity(intent);
            }
        });


    }



}
