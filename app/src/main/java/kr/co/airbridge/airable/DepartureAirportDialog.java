package kr.co.airbridge.airable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DepartureAirportDialog extends Dialog {

    private View.OnClickListener mLeftClickListener;
    private AdapterView.OnItemClickListener itemClickListener;

    @Bind(R.id.custom_close)
    ImageButton mLeftButton;

    @Bind(R.id.departure_airport_listview)
    ListView date_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_departure_airport_dialog);
        ButterKnife.bind(this);

        // 클릭 이벤트 셋팅
        if (mLeftClickListener != null) {
            mLeftButton.setOnClickListener(mLeftClickListener);
        }

        if(itemClickListener != null){
            date_listview.setOnItemClickListener(itemClickListener);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.tab_list_textview);


        // 아이템을 추가
        adapter.add("에어로로직");
        adapter.add("사천항공");
        adapter.add("세부퍼시픽항공");
        adapter.add("유피에스항공");
        adapter.add("아틀라스항공");
        adapter.add("제주항공");
        adapter.add("비즈니스에어");
        adapter.add("중국우정항공");
        adapter.add("미국남부화물항공");
        adapter.add("아메리칸항공");
        adapter.add("에어캐나다");
        adapter.add("만다린항공");
        adapter.add("에어 프랑스");
        adapter.add("에어인디아");
        adapter.add("핀에어");
        adapter.add("유니항공");
        adapter.add("영국항공");
        adapter.add("에바항공");
        adapter.add("중국국제항공");
        adapter.add("중화항공");
        adapter.add("중국화물항공");
        adapter.add("카고룩스항공");
        adapter.add("캐세이패시픽항공");
        adapter.add("중국남방항공");
        adapter.add("다이나믹 에어");
        adapter.add("에어아시아엑스");
        adapter.add("델타항공");
        adapter.add("에미레이트항공");
        adapter.add("에티오피아항공");
        adapter.add("에티하드 항공");
        adapter.add("에버그린인터내셔날");
        adapter.add("상하이항공");
        adapter.add("페덱스");
        adapter.add("가루다인도네시아항공");
        adapter.add("텐진 에어라인");
        adapter.add("피시 에어라인");
        adapter.add("하와이안항공");
        adapter.add("홍콩항공");
        adapter.add("우즈베키스탄항공");
        adapter.add("오로라항공");
        adapter.add("일본항공");
        adapter.add("바닐라 에어");
        adapter.add("에어아스타나");
        adapter.add("대한항공");
        adapter.add("에어인천");
        adapter.add("케이엘엠네덜란드항공");
        adapter.add("에어 비쉬켁");
        adapter.add("일본화물항공");
        adapter.add("에어홍콩");
        adapter.add("루프트한자항공");
        adapter.add("진에어");
        adapter.add("메가몰디브항공");
        adapter.add("중국하문항공");
        adapter.add("말레이시아항공");
        adapter.add("피치항공");
        adapter.add("중국동방항공");
        adapter.add("전일본공수");
        adapter.add("에어재팬");
        adapter.add("에어 마카오");
        adapter.add("순풍항공");
        adapter.add("체코항공");
        adapter.add("몽골항공");
        adapter.add("아시아나항공");
        adapter.add("폴라에어카고");
        adapter.add("필리핀항공");
        adapter.add("카타르항공");
        adapter.add("라오항공");
        adapter.add("야쿠티아 항공");
        adapter.add("에어브리지 카고 에어라인");
        adapter.add("산동항공");
        adapter.add("싱가포르항공");
        adapter.add("에어로플로트항공");
        adapter.add("타이항공");
        adapter.add("터키항공");
        adapter.add("티웨이항공");
        adapter.add("스쿳 항공");
        adapter.add("유나이티드항공");
        adapter.add("홍콩엑스프레스항공");
        adapter.add("비엣제트항공");
        adapter.add("베트남항공");
        adapter.add("월드항공");
        adapter.add("타이에어아시아엑스");
        adapter.add("양쯔강익스프레스항공");
        adapter.add("에어아시아 제스트항공");
        adapter.add("스카이윙스 아시아 항공");
        adapter.add("이스타항공");
        adapter.add("심천항공");


        // ListView 가져오기
        ListView listView = (ListView) findViewById(R.id.departure_airport_listview);

        // ListView에 각각의 아이템표시를 제어하는 Adapter를 설정
        listView.setAdapter(adapter);
        listView.setBackgroundColor(0xFFFFFFFF);
        // 아이템을 [클릭]시의 이벤트 리스너를 등록



    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public DepartureAirportDialog(Context context,
                                  View.OnClickListener singleListener, AdapterView.OnItemClickListener itemClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mLeftClickListener = singleListener;
        this.itemClickListener = itemClickListener;

    }



}
