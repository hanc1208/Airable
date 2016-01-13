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
