package kr.co.airbridge.airable;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.airbridge.airable.utility.ActivityUtility;

public class AirInfoListActivity extends AppCompatActivity {


    private ListView    m_ListView;
    private AirInfoListAdapter  m_Adapter;
    private ArrayList<DepartingFlight> m_List;

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

        DepartingFlight Item1 = new DepartingFlight("00:10","","SQ603","싱가포르 항공","싱가포르");
        DepartingFlight Item2 = new DepartingFlight("00:20","00:40","OZ6783","아시아나 항공","홍콩");
        DepartingFlight Item3 = new DepartingFlight("04:10","","VA5405","버진 오스트레일리아","싱가포르");
        DepartingFlight Item4 = new DepartingFlight("05:10","","UO1621","홍콩 익스프레스","홍콩");
        DepartingFlight Item5 = new DepartingFlight("06:30","07:30","FG3023","대한항공","이스탄불");
        DepartingFlight Item6 = new DepartingFlight("10:10","","GJ2938","싱가포르 항공","암스테르담");
        DepartingFlight Item7 = new DepartingFlight("12:20","","HH2934","나리타항공","도쿄");
        DepartingFlight Item8 = new DepartingFlight("14:10","","HI2945","아시아나 항공","이스탄불");
        DepartingFlight Item9 = new DepartingFlight("15:10","16:00","AH2048","나리타항공","오사카");
        DepartingFlight Item10 = new DepartingFlight("18:30","","HG9183","대한항공","제주도");

        m_List.add(Item1);
        m_List.add(Item2);
        m_List.add(Item3);
        m_List.add(Item4);
        m_List.add(Item5);
        m_List.add(Item6);
        m_List.add(Item7);
        m_List.add(Item8);
        m_List.add(Item9);
        m_List.add(Item10);




        Intent intent = getIntent();

        int requestCode = (int)intent.getIntExtra("requestCode", -1);

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

                //intent.putExtra("list","HI!!");

                startActivity(intent);
            }
        });


    }

}
