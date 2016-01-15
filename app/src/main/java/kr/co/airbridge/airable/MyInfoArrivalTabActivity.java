package kr.co.airbridge.airable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyInfoArrivalTabActivity extends Activity{

    private ListView m_ListView;
    private MyAirInfoDepartureListAdapter  m_Adapter;
    private ArrayList<DepartureFlight> m_List;

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

        DepartureFlight Item1 = new DepartureFlight("00:10","","SQ603","마감예정","싱가포르","L01-M12","34");
        DepartureFlight Item2 = new DepartureFlight("00:20","00:40","OZ6783","마감예정","홍콩","L11-G12","12");
        DepartureFlight Item3 = new DepartureFlight("04:10","","VA5405","마감예정","싱가포르","D01-A12","53");
        DepartureFlight Item4 = new DepartureFlight("05:10","","UO1621","마감예정","홍콩","G01-J12","12");
        DepartureFlight Item5 = new DepartureFlight("06:30","07:30","FG3023","탑승준비","이스탄불","Y01-M12","53");
        DepartureFlight Item6 = new DepartureFlight("10:10","","GJ2938","탑승준비","암스테르담","E01-M12","64");
        DepartureFlight Item7 = new DepartureFlight("12:20","","HH2934","탑승준비","도쿄","L01-H12","13");
        DepartureFlight Item8 = new DepartureFlight("14:10","","HI2945","탑승준비","이스탄불","U01-M12","15");
        DepartureFlight Item9 = new DepartureFlight("15:10","16:00","AH2048","체크인 종료","오사카","W01-C12","43");
        DepartureFlight Item10 = new DepartureFlight("18:30","","HG9183","체크인 종료","제주도","B01-D12","53");

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
