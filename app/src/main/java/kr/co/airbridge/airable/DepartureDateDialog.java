package kr.co.airbridge.airable;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DepartureDateDialog extends Dialog{
    private View.OnClickListener mLeftClickListener;
    private AdapterView.OnItemClickListener itemClickListener;

    @Bind(R.id.custom_close)
    ImageButton mLeftButton;

    @Bind(R.id.departure_date_listview)
    ListView date_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_departure_date_dialog);
        ButterKnife.bind(this);

        // 클릭 이벤트 셋팅
        if (mLeftClickListener != null) {
            mLeftButton.setOnClickListener(mLeftClickListener);
        }

        if(itemClickListener != null){
            date_listview.setOnItemClickListener(itemClickListener);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.tab_list_textview);

        /*
        long now = System.currentTimeMillis();
        Date departure_date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy.MM.dd");
        String strNow = sdfNow.format(departure_date);

        Calendar calendar = Calendar.getInstance();

        Integer dayNow = calendar.getTime().getDate();



        Date date = calendar.getTime();
        String week = new SimpleDateFormat("EE", Locale.KOREAN).format(date.getTime());

        Log.i("date",week);


        Log.i("strNow",strNow);

        Log.i("dayNow",dayNow.toString());


        */


        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK,new Date().getDate() );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd EE",Locale.KOREAN);

        for(int i=0;i<7;i++){
            Log.i("dateTag",simpleDateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK,1);
        }

        // 아이템을 추가
        adapter.add("item1");
        adapter.add("item2");
        adapter.add("item3");
        adapter.add("item4");
        adapter.add("item5");
        adapter.add("item6");
        adapter.add("item7");
        adapter.add("item8");
        adapter.add("item9");

        // ListView 가져오기
        ListView listView = (ListView) findViewById(R.id.departure_date_listview);

        // ListView에 각각의 아이템표시를 제어하는 Adapter를 설정
        listView.setAdapter(adapter);
        listView.setBackgroundColor(0xFFFFFFFF);
        // 아이템을 [클릭]시의 이벤트 리스너를 등록



    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public DepartureDateDialog(Context context,
                               View.OnClickListener singleListener, AdapterView.OnItemClickListener itemClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mLeftClickListener = singleListener;
        this.itemClickListener = itemClickListener;

    }






}
