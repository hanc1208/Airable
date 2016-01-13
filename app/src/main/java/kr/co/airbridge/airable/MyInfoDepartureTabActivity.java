package kr.co.airbridge.airable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyInfoDepartureTabActivity extends Activity{


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


        DepartureFlight Item1 = new DepartureFlight("dd","ff","sasdfs","ff","daad","sss","ffgfg");
        DepartureFlight Item2 = new DepartureFlight("ddd","ff","ss","ff","dffd","sss","ffgfg");
        DepartureFlight Item3 = new DepartureFlight("ddf","ff","ss","fffdasfd","dsasd","sss","ffgfg");
        DepartureFlight Item4 = new DepartureFlight("ddg","ff","ss","ff","dd","sss","ffgfg");
        DepartureFlight Item5 = new DepartureFlight("ddds","ff","ss","fdfaf","fdsdd","sss","ffgfg");
        DepartureFlight Item6 = new DepartureFlight("addd","ff","ss","ff","dd","sss","ffgfg");
        DepartureFlight Item7 = new DepartureFlight("fdsddd","ff","ss","fdsasdff","dfdsfd","sss","ffgfg");
        DepartureFlight Item8 = new DepartureFlight("asdfddf","ff","ss","ff","dd","sss","ffgfg");
        DepartureFlight Item9 = new DepartureFlight("fdsddg","ff","ss","fffds","dddd","sss","ffgfg");
        DepartureFlight Item10 = new DepartureFlight("adfddddds","ff","ss","ff","dssd","sss","ffgfg");

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

