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


public class DepartureCityDialog extends Dialog{

    private View.OnClickListener mLeftClickListener;
    private AdapterView.OnItemClickListener itemClickListener;

    @Bind(R.id.custom_close)
    ImageButton mLeftButton;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.tab_list_textview);


        // 아이템을 추가
        adapter.add("citem1");
        adapter.add("citem2");
        adapter.add("citem3");
        adapter.add("citem4");
        adapter.add("citem5");
        adapter.add("citem6");
        adapter.add("citem7");
        adapter.add("citem8");
        adapter.add("citem9");

        // ListView 가져오기
        ListView listView = (ListView) findViewById(R.id.departure_city_listview);

        // ListView에 각각의 아이템표시를 제어하는 Adapter를 설정
        listView.setAdapter(adapter);
        listView.setBackgroundColor(0xFFFFFFFF);
        // 아이템을 [클릭]시의 이벤트 리스너를 등록



    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public DepartureCityDialog(Context context,
                               View.OnClickListener singleListener, AdapterView.OnItemClickListener itemClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mLeftClickListener = singleListener;
        this.itemClickListener = itemClickListener;
    }



}
