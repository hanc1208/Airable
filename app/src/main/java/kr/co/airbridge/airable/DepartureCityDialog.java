package kr.co.airbridge.airable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DepartureCityDialog extends Dialog{

    private ListView    m_ListView;
    private DepartureCityDialogAdapter  m_Adapter;
    private ArrayList<AirportCodeModel> m_List;

    private View.OnClickListener mLeftClickListener;
    private AdapterView.OnItemClickListener itemClickListener;

    @Bind(R.id.custom_close)
    ImageView mLeftButton;

    @Bind(R.id.departure_city_listview)
    ListView city_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_departure_city_dialog);
        ButterKnife.bind(this);

        // 클릭 이벤트 셋팅
        if (mLeftClickListener != null) {
            mLeftButton.setOnClickListener(mLeftClickListener);
        }

        if(itemClickListener != null){
            city_listview.setOnItemClickListener(itemClickListener);
        }




        m_List = new ArrayList<>();

        // 커스텀 어댑터 생성
        m_Adapter = new DepartureCityDialogAdapter(m_List);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.departure_city_listview);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);


        AirportCodeModel 	Item1	=	new AirportCodeModel("아사히카와","AKJ");
        AirportCodeModel 	Item2	=	new AirportCodeModel("오클랜드","AKL");
        AirportCodeModel 	Item3	=	new AirportCodeModel("알마티","ALA");
        AirportCodeModel 	Item4	=	new AirportCodeModel("암스테르담","AMS");
        AirportCodeModel 	Item5	=	new AirportCodeModel("앵커리지","ANC");
        AirportCodeModel 	Item6	=	new AirportCodeModel("아오모리","AOJ");
        AirportCodeModel 	Item7	=	new AirportCodeModel("아쉬가바트","ASB");
        AirportCodeModel 	Item8	=	new AirportCodeModel("애틀랜타","ATL");
        AirportCodeModel 	Item9	=	new AirportCodeModel("아부다비","AUH");
        AirportCodeModel 	Item10	=	new AirportCodeModel("바레인","BAH");
        AirportCodeModel 	Item11	=	new AirportCodeModel("베이루트","BEY");
        AirportCodeModel 	Item12	=	new AirportCodeModel("코타키나발루","BKI");
        AirportCodeModel 	Item13	=	new AirportCodeModel("방콕","BKK");
        AirportCodeModel 	Item14	=	new AirportCodeModel("브리즈번","BNE");
        AirportCodeModel 	Item15	=	new AirportCodeModel("뭄바이","BOM");
        AirportCodeModel 	Item16	=	new AirportCodeModel("브뤼셀","BRU");
        AirportCodeModel 	Item17	=	new AirportCodeModel("바젤","BSL");
        AirportCodeModel 	Item18	=	new AirportCodeModel("부에노스 아이레스","BUE");
        AirportCodeModel 	Item19	=	new AirportCodeModel("반다르스리브가완","BWN");
        AirportCodeModel 	Item20	=	new AirportCodeModel("카이로","CAI");
        AirportCodeModel 	Item21	=	new AirportCodeModel("광저우","CAN");
        AirportCodeModel 	Item22	=	new AirportCodeModel("마이케티아","CCS");
        AirportCodeModel 	Item23	=	new AirportCodeModel("파리","CDG");
        AirportCodeModel 	Item24	=	new AirportCodeModel("세부","CEB");
        AirportCodeModel 	Item25	=	new AirportCodeModel("자카르타","CGK");
        AirportCodeModel 	Item26	=	new AirportCodeModel("쾰른","CGN");
        AirportCodeModel 	Item27	=	new AirportCodeModel("정저우","CGO");
        AirportCodeModel 	Item28	=	new AirportCodeModel("창춘","CGQ");
        AirportCodeModel 	Item29	=	new AirportCodeModel("제주","CJU");
        AirportCodeModel 	Item30	=	new AirportCodeModel("충칭","CKG");
        AirportCodeModel 	Item31	=	new AirportCodeModel("콜롬보","CMB");
        AirportCodeModel 	Item32	=	new AirportCodeModel("치앙마이","CNX");
        AirportCodeModel 	Item33	=	new AirportCodeModel("코펜하겐","CPH");
        AirportCodeModel 	Item34	=	new AirportCodeModel("클라크필드","CRK");
        AirportCodeModel 	Item35	=	new AirportCodeModel("장사","CSX");
        AirportCodeModel 	Item36	=	new AirportCodeModel("삿포로","CTS");
        AirportCodeModel 	Item37	=	new AirportCodeModel("청두","CTU");
        AirportCodeModel 	Item38	=	new AirportCodeModel("다카","DAC");
        AirportCodeModel 	Item39	=	new AirportCodeModel("델리","DEL");
        AirportCodeModel 	Item40	=	new AirportCodeModel("댈러스","DFW");
        AirportCodeModel 	Item41	=	new AirportCodeModel("다롄","DLC");
        AirportCodeModel 	Item42	=	new AirportCodeModel("도하","DOH");
        AirportCodeModel 	Item43	=	new AirportCodeModel("덴파사르","DPS");
        AirportCodeModel 	Item44	=	new AirportCodeModel("디트로이트","DTW");
        AirportCodeModel 	Item45	=	new AirportCodeModel("두바이","DXB");
        AirportCodeModel 	Item46	=	new AirportCodeModel("로마","FCO");
        AirportCodeModel 	Item47	=	new AirportCodeModel("후쿠시마","FKS");
        AirportCodeModel 	Item48	=	new AirportCodeModel("프랑크푸르트","FRA");
        AirportCodeModel 	Item49	=	new AirportCodeModel("비슈케크","FRU");
        AirportCodeModel 	Item50	=	new AirportCodeModel("시즈오카","FSZ");
        AirportCodeModel 	Item51	=	new AirportCodeModel("후쿠오카","FUK");
        AirportCodeModel 	Item52	=	new AirportCodeModel("서울/ 김포","GMP");
        AirportCodeModel 	Item53	=	new AirportCodeModel("고테버그","GOT");
        AirportCodeModel 	Item54	=	new AirportCodeModel("상파울루","GRU");
        AirportCodeModel 	Item55	=	new AirportCodeModel("괌","GUM");
        AirportCodeModel 	Item56	=	new AirportCodeModel("바쿠","GYD");
        AirportCodeModel 	Item57	=	new AirportCodeModel("하노이","HAN");
        AirportCodeModel 	Item58	=	new AirportCodeModel("헬싱키","HEL");
        AirportCodeModel 	Item59	=	new AirportCodeModel("항저우","HGH");
        AirportCodeModel 	Item60	=	new AirportCodeModel("히로시마","HIJ");
        AirportCodeModel 	Item61	=	new AirportCodeModel("하코다테","HKD");
        AirportCodeModel 	Item62	=	new AirportCodeModel("홍콩","HKG");
        AirportCodeModel 	Item63	=	new AirportCodeModel("푸케트","HKT");
        AirportCodeModel 	Item64	=	new AirportCodeModel("도쿄/ 하네다","HND");
        AirportCodeModel 	Item65	=	new AirportCodeModel("호놀룰루","HNL");
        AirportCodeModel 	Item66	=	new AirportCodeModel("휴스턴","HOU");
        AirportCodeModel 	Item67	=	new AirportCodeModel("하얼빈","HRB");
        AirportCodeModel 	Item68	=	new AirportCodeModel("워싱턴","IAD");
        AirportCodeModel 	Item69	=	new AirportCodeModel("이르쿠츠크","IKT");
        AirportCodeModel 	Item70	=	new AirportCodeModel("에어본  에어파크","ILN");
        AirportCodeModel 	Item71	=	new AirportCodeModel("인디애나폴리스","IND");
        AirportCodeModel 	Item72	=	new AirportCodeModel("이스탄불","IST");
        AirportCodeModel 	Item73	=	new AirportCodeModel("뉴욕","JFK");
        AirportCodeModel 	Item74	=	new AirportCodeModel("가오슝","KHH");
        AirportCodeModel 	Item75	=	new AirportCodeModel("난창","KHN");
        AirportCodeModel 	Item76	=	new AirportCodeModel("하바로프스크","KHV");
        AirportCodeModel 	Item77	=	new AirportCodeModel("니가타","KIJ");
        AirportCodeModel 	Item78	=	new AirportCodeModel("오사카/ 간사이","KIX");
        AirportCodeModel 	Item79	=	new AirportCodeModel("기타큐슈","KKJ");
        AirportCodeModel 	Item80	=	new AirportCodeModel("칼리보","KLO");
        AirportCodeModel 	Item81	=	new AirportCodeModel("쿤밍","KMG");
        AirportCodeModel 	Item82	=	new AirportCodeModel("미야자키","KMI");
        AirportCodeModel 	Item83	=	new AirportCodeModel("구마모토","KMJ");
        AirportCodeModel 	Item84	=	new AirportCodeModel("고마쓰","KMQ");
        AirportCodeModel 	Item85	=	new AirportCodeModel("가고시마","KOJ");
        AirportCodeModel 	Item86	=	new AirportCodeModel("카트만두","KTM");
        AirportCodeModel 	Item87	=	new AirportCodeModel("쿠알라룸푸르","KUL");
        AirportCodeModel 	Item88	=	new AirportCodeModel("쿠웨이트","KWI");
        AirportCodeModel 	Item89	=	new AirportCodeModel("계림","KWL");
        AirportCodeModel 	Item90	=	new AirportCodeModel("라스베이거스","LAS");
        AirportCodeModel 	Item91	=	new AirportCodeModel("로스앤젤레스","LAX");
        AirportCodeModel 	Item92	=	new AirportCodeModel("상트페테르부르크","LED");
        AirportCodeModel 	Item93	=	new AirportCodeModel("런던/히드로","LHR");
        AirportCodeModel 	Item94	=	new AirportCodeModel("리장","LJG");
        AirportCodeModel 	Item95	=	new AirportCodeModel("룩셈부르크","LUX");
        AirportCodeModel 	Item96	=	new AirportCodeModel("첸나이","MAA");
        AirportCodeModel 	Item97	=	new AirportCodeModel("마드리드","MAD");
        AirportCodeModel 	Item98	=	new AirportCodeModel("무단장","MDG");
        AirportCodeModel 	Item99	=	new AirportCodeModel("멜버른","MEL");
        AirportCodeModel 	Item100	=	new AirportCodeModel("멤피스","MEM");
        AirportCodeModel 	Item101	=	new AirportCodeModel("마카오","MFM");
        AirportCodeModel 	Item102	=	new AirportCodeModel("마이애미","MIA");
        AirportCodeModel 	Item103	=	new AirportCodeModel("마닐라","MNL");
        AirportCodeModel 	Item104	=	new AirportCodeModel("뮌헨","MUC");
        AirportCodeModel 	Item105	=	new AirportCodeModel("밀라노","MXP");
        AirportCodeModel 	Item106	=	new AirportCodeModel("마쓰야마","MYJ");
        AirportCodeModel 	Item107	=	new AirportCodeModel("난디","NAN");
        AirportCodeModel 	Item108	=	new AirportCodeModel("닝보","NGB");
        AirportCodeModel 	Item109	=	new AirportCodeModel("나고야","NGO");
        AirportCodeModel 	Item110	=	new AirportCodeModel("나가사키","NGS");
        AirportCodeModel 	Item111	=	new AirportCodeModel("난징","NKG");
        AirportCodeModel 	Item112	=	new AirportCodeModel("난닝","NNG");
        AirportCodeModel 	Item113	=	new AirportCodeModel("누메아","NOU");
        AirportCodeModel 	Item114	=	new AirportCodeModel("도쿄/ 나리타","NRT");
        AirportCodeModel 	Item115	=	new AirportCodeModel("오이타","OIT");
        AirportCodeModel 	Item116	=	new AirportCodeModel("오키나와","OKA");
        AirportCodeModel 	Item117	=	new AirportCodeModel("오카야마","OKJ");
        AirportCodeModel 	Item118	=	new AirportCodeModel("시카고","ORD");
        AirportCodeModel 	Item119	=	new AirportCodeModel("오사카","OSA");
        AirportCodeModel 	Item120	=	new AirportCodeModel("오슬로","OSL");
        AirportCodeModel 	Item121	=	new AirportCodeModel("노보시비르스크","OVB");
        AirportCodeModel 	Item122	=	new AirportCodeModel("베이징","PEK");
        AirportCodeModel 	Item123	=	new AirportCodeModel("페낭","PEN");
        AirportCodeModel 	Item124	=	new AirportCodeModel("필라델피아","PHL");
        AirportCodeModel 	Item125	=	new AirportCodeModel("프놈펜","PNH");
        AirportCodeModel 	Item126	=	new AirportCodeModel("프라하","PRG");
        AirportCodeModel 	Item127	=	new AirportCodeModel("부산","PUS");
        AirportCodeModel 	Item128	=	new AirportCodeModel("상하이/ 푸동","PVG");
        AirportCodeModel 	Item129	=	new AirportCodeModel("시엠립","REP");
        AirportCodeModel 	Item130	=	new AirportCodeModel("양곤","RGN");
        AirportCodeModel 	Item131	=	new AirportCodeModel("코로르","ROR");
        AirportCodeModel 	Item132	=	new AirportCodeModel("센다이","SDJ");
        AirportCodeModel 	Item133	=	new AirportCodeModel("시애틀","SEA");
        AirportCodeModel 	Item134	=	new AirportCodeModel("샌프란시스코","SFO");
        AirportCodeModel 	Item135	=	new AirportCodeModel("수비크","SFS");
        AirportCodeModel 	Item136	=	new AirportCodeModel("호찌민","SGN");
        AirportCodeModel 	Item137	=	new AirportCodeModel("선양","SHE");
        AirportCodeModel 	Item138	=	new AirportCodeModel("서안","SIA");
        AirportCodeModel 	Item139	=	new AirportCodeModel("싱가포르","SIN");
        AirportCodeModel 	Item140	=	new AirportCodeModel("스자좡","SJW");
        AirportCodeModel 	Item141	=	new AirportCodeModel("소피아","SOF");
        AirportCodeModel 	Item142	=	new AirportCodeModel("사이판","SPN");
        AirportCodeModel 	Item143	=	new AirportCodeModel("산티아고","STI");
        AirportCodeModel 	Item144	=	new AirportCodeModel("스톡홀름","STO");
        AirportCodeModel 	Item145	=	new AirportCodeModel("모스크바","SVO");
        AirportCodeModel 	Item146	=	new AirportCodeModel("시드니","SYD");
        AirportCodeModel 	Item147	=	new AirportCodeModel("싼야","SYX");
        AirportCodeModel 	Item148	=	new AirportCodeModel("선전","SZX");
        AirportCodeModel 	Item149	=	new AirportCodeModel("대구","TAE");
        AirportCodeModel 	Item150	=	new AirportCodeModel("다카마쓰","TAK");
        AirportCodeModel 	Item151	=	new AirportCodeModel("칭다오","TAO");
        AirportCodeModel 	Item152	=	new AirportCodeModel("타슈켄트","TAS");
        AirportCodeModel 	Item153	=	new AirportCodeModel("테헤란","THR");
        AirportCodeModel 	Item154	=	new AirportCodeModel("텔아비브","TLV");
        AirportCodeModel 	Item155	=	new AirportCodeModel("지난","TNA");
        AirportCodeModel 	Item156	=	new AirportCodeModel("도야마","TOY");
        AirportCodeModel 	Item157	=	new AirportCodeModel("타이페이","TPE");
        AirportCodeModel 	Item158	=	new AirportCodeModel("아스타나","TSE");
        AirportCodeModel 	Item159	=	new AirportCodeModel("톈진","TSN");
        AirportCodeModel 	Item160	=	new AirportCodeModel("울란바토르","ULN");
        AirportCodeModel 	Item161	=	new AirportCodeModel("우루무치","URC");
        AirportCodeModel 	Item162	=	new AirportCodeModel("사할린","UUS");
        AirportCodeModel 	Item163	=	new AirportCodeModel("비엔나","VIE");
        AirportCodeModel 	Item164	=	new AirportCodeModel("블라디보스토크","VVO");
        AirportCodeModel 	Item165	=	new AirportCodeModel("바르샤바","WAW");
        AirportCodeModel 	Item166	=	new AirportCodeModel("웨이하이","WEH");
        AirportCodeModel 	Item167	=	new AirportCodeModel("샤먼","XMN");
        AirportCodeModel 	Item168	=	new AirportCodeModel("요나고","YGJ");
        AirportCodeModel 	Item169	=	new AirportCodeModel("야쿠츠크","YKS");
        AirportCodeModel 	Item170	=	new AirportCodeModel("옌지","YNJ");
        AirportCodeModel 	Item171	=	new AirportCodeModel("옌타이","YNT");
        AirportCodeModel 	Item172	=	new AirportCodeModel("옌청","YNZ");
        AirportCodeModel 	Item173	=	new AirportCodeModel("밴쿠버","YVR");
        AirportCodeModel 	Item174	=	new AirportCodeModel("캘거리","YYC");
        AirportCodeModel 	Item175	=	new AirportCodeModel("토론토","YYZ");
        AirportCodeModel 	Item176	=	new AirportCodeModel("취리히","ZRH");



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
        m_List.add(Item11);
        m_List.add(Item12);
        m_List.add(Item13);
        m_List.add(Item14);
        m_List.add(Item15);
        m_List.add(Item16);
        m_List.add(Item17);
        m_List.add(Item18);
        m_List.add(Item19);
        m_List.add(Item20);
        m_List.add(Item21);
        m_List.add(Item22);
        m_List.add(Item23);
        m_List.add(Item24);
        m_List.add(Item25);
        m_List.add(Item26);
        m_List.add(Item27);
        m_List.add(Item28);
        m_List.add(Item29);
        m_List.add(Item30);
        m_List.add(Item31);
        m_List.add(Item32);
        m_List.add(Item33);
        m_List.add(Item34);
        m_List.add(Item35);
        m_List.add(Item36);
        m_List.add(Item37);
        m_List.add(Item38);
        m_List.add(Item39);
        m_List.add(Item40);
        m_List.add(Item41);
        m_List.add(Item42);
        m_List.add(Item43);
        m_List.add(Item44);
        m_List.add(Item45);
        m_List.add(Item46);
        m_List.add(Item47);
        m_List.add(Item48);
        m_List.add(Item49);
        m_List.add(Item50);
        m_List.add(Item51);
        m_List.add(Item52);
        m_List.add(Item53);
        m_List.add(Item54);
        m_List.add(Item55);
        m_List.add(Item56);
        m_List.add(Item57);
        m_List.add(Item58);
        m_List.add(Item59);
        m_List.add(Item60);
        m_List.add(Item61);
        m_List.add(Item62);
        m_List.add(Item63);
        m_List.add(Item64);
        m_List.add(Item65);
        m_List.add(Item66);
        m_List.add(Item67);
        m_List.add(Item68);
        m_List.add(Item69);
        m_List.add(Item70);
        m_List.add(Item71);
        m_List.add(Item72);
        m_List.add(Item73);
        m_List.add(Item74);
        m_List.add(Item75);
        m_List.add(Item76);
        m_List.add(Item77);
        m_List.add(Item78);
        m_List.add(Item79);
        m_List.add(Item80);
        m_List.add(Item81);
        m_List.add(Item82);
        m_List.add(Item83);
        m_List.add(Item84);
        m_List.add(Item85);
        m_List.add(Item86);
        m_List.add(Item87);
        m_List.add(Item88);
        m_List.add(Item89);
        m_List.add(Item90);
        m_List.add(Item91);
        m_List.add(Item92);
        m_List.add(Item93);
        m_List.add(Item94);
        m_List.add(Item95);
        m_List.add(Item96);
        m_List.add(Item97);
        m_List.add(Item98);
        m_List.add(Item99);
        m_List.add(Item100);
        m_List.add(Item101);
        m_List.add(Item102);
        m_List.add(Item103);
        m_List.add(Item104);
        m_List.add(Item105);
        m_List.add(Item106);
        m_List.add(Item107);
        m_List.add(Item108);
        m_List.add(Item109);
        m_List.add(Item110);
        m_List.add(Item111);
        m_List.add(Item112);
        m_List.add(Item113);
        m_List.add(Item114);
        m_List.add(Item115);
        m_List.add(Item116);
        m_List.add(Item117);
        m_List.add(Item118);
        m_List.add(Item119);
        m_List.add(Item120);
        m_List.add(Item121);
        m_List.add(Item122);
        m_List.add(Item123);
        m_List.add(Item124);
        m_List.add(Item125);
        m_List.add(Item126);
        m_List.add(Item127);
        m_List.add(Item128);
        m_List.add(Item129);
        m_List.add(Item130);
        m_List.add(Item131);
        m_List.add(Item132);
        m_List.add(Item133);
        m_List.add(Item134);
        m_List.add(Item135);
        m_List.add(Item136);
        m_List.add(Item137);
        m_List.add(Item138);
        m_List.add(Item139);
        m_List.add(Item140);
        m_List.add(Item141);
        m_List.add(Item142);
        m_List.add(Item143);
        m_List.add(Item144);
        m_List.add(Item145);
        m_List.add(Item146);
        m_List.add(Item147);
        m_List.add(Item148);
        m_List.add(Item149);
        m_List.add(Item150);
        m_List.add(Item151);
        m_List.add(Item152);
        m_List.add(Item153);
        m_List.add(Item154);
        m_List.add(Item155);
        m_List.add(Item156);
        m_List.add(Item157);
        m_List.add(Item158);
        m_List.add(Item159);
        m_List.add(Item160);
        m_List.add(Item161);
        m_List.add(Item162);
        m_List.add(Item163);
        m_List.add(Item164);
        m_List.add(Item165);
        m_List.add(Item166);
        m_List.add(Item167);
        m_List.add(Item168);
        m_List.add(Item169);
        m_List.add(Item170);
        m_List.add(Item171);
        m_List.add(Item172);
        m_List.add(Item173);
        m_List.add(Item174);
        m_List.add(Item175);
        m_List.add(Item176);

        m_ListView.setBackgroundColor(0xffffffff);


    /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.tab_list_textview);


        // 아이템을 추가


        // ListView 가져오기
        ListView listView = (ListView) findViewById(R.id.departure_city_listview);

        // ListView에 각각의 아이템표시를 제어하는 Adapter를 설정
        listView.setAdapter(adapter);


        listView.setBackgroundColor(0xFFFFFFFF);
        // 아이템을 [클릭]시의 이벤트 리스너를 등록

*/

    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public DepartureCityDialog(Context context,
                               View.OnClickListener singleListener, AdapterView.OnItemClickListener itemClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mLeftClickListener = singleListener;
        this.itemClickListener = itemClickListener;
    }



}
