package kr.co.airbridge.airable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyInfoDepartureTabActivity extends Activity{


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
        m_List.add(Item10);

    }
}

