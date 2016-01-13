package kr.co.airbridge.airable;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchShopsActivity extends AppCompatActivity implements FloorButtonListener{
    SearchShopsAdapter shopsAdapter;
    FragmentManager fragmentManager;
    int curFloor = -1; // 10 11 12 13 14 : 교통센터 (B1f~4f), 20 21 22 23 24 : 터미널, 30 31 32 : 탑승동

    @Bind(R.id.searchshops_edittext)
    EditText edittext;
    @Bind(R.id.searchshops_listview)
    ListView listview;
    @Bind(R.id.searchshops_floor_button)
    Button floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shops);
        fragmentManager = getFragmentManager();

        ButterKnife.bind(this);

        // EditText의 줄이 늘어나지 못하도록 설정한다.
        edittext.setSingleLine();
        // 키패드의 Search 버튼을 누를 경우 검색 이벤트를 실행한다.
        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SearchShops();
                    return true;
                }
                return false;
            }
        });

        // SearchShopsAdapter 생성 및 연결
        shopsAdapter = new SearchShopsAdapter();
        listview.setAdapter(shopsAdapter);
    }

    // Reset 버튼을 누르면 EditText의 내용들을 삭제한다.
    @OnClick(R.id.searchshops_reset_button)
    public void onResetClick() {
        edittext.setText("");
    }

    // Map 버튼 이벤트
    @OnClick(R.id.searchshops_map_button)
    public void onMapClick() {
        Intent i = new Intent(this, SearchMapActivity.class);
        i.putExtra("searchkeyword",edittext.getText().toString() );
        i.putExtra("curFloor", curFloor);
        this.startActivity(i);
    }

    // 층 버튼 이벤트
    @OnClick(R.id.searchshops_floor_button)
    public void onFloorClick() {
        edittext.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(edittext.getWindowToken(), 0);

        FloorFragment floorFragment = new FloorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("curfloor", curFloor);
        floorFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .add(R.id.searchshops_fragment_container, floorFragment, "FLOOR_FRAGMENT")
                .commit();
    }

    // EditText에서 Search Action 발생 시, 장소를 검색하는 Method.
    public void SearchShops() {
        String searchText = edittext.getText().toString();

        // 키보드 내리기
        edittext.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(edittext.getWindowToken(), 0);

        shopsAdapter.clearList();  // 검색 후 List 수정

        // Test code starts
        for (int i = 0; i < searchText.length(); i++) {
            Shop tempShop = new Shop(searchText + i, "음식", "매장 위치" + i, "00 : 00 ~ 23 : 59", "012-3456-7890");
            shopsAdapter.addItem(tempShop);
        }
        // Test code ends
        shopsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFloorSelectClick(View view) {
        FloorFragment viewer = null;
        try {
            viewer = (FloorFragment) getFragmentManager().findFragmentByTag("FLOOR_FRAGMENT");
            viewer.floorButtonClick(view);
            Log.i("mytag", "onFloorSelectClick");
        } catch (ClassCastException e) {
            Log.i("mytag", e.getMessage());
        }
    }

    @Override
    public void setCurFloor(int curFloor){
        this.curFloor = curFloor;
        String tempStr;
        switch(curFloor){
            case 10:
                tempStr = "교통센터 B1F";
                break;
            case 11:
                tempStr = "교통센터 1F";
                break;
            case 12:
                tempStr = "교통센터 2F";
                break;
            case 13:
                tempStr = "교통센터 3F";
                break;
            case 14:
                tempStr = "교통센터 4F";
                break;
            case 20:
                tempStr = "여객터미널 B1F";
                break;
            case 21:
                tempStr = "여객터미널 1F";
                break;
            case 22:
                tempStr = "여객터미널 2F";
                break;
            case 23:
                tempStr = "여객터미널 3F";
                break;
            case 24:
                tempStr = "여객터미널 4F";
                break;
            case 30:
                tempStr = "탑승동 B1F";
                break;
            case 31:
                tempStr = "탑승동 1F";
                break;
            case 32:
                tempStr = "탑승동 2F";
                break;
            default:
                tempStr = "ALL";
                break;
        }
        floor.setText(tempStr);
        SearchShops();
    }
}
