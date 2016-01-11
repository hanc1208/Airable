package kr.co.airbridge.airable;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchShopsActivity extends AppCompatActivity {
    SearchShopsAdapter shopsAdapter;

    @Bind(R.id.searchshops_edittext)
    EditText edittext;
    @Bind(R.id.searchshops_listview)
    ListView listview;
    @Bind(R.id.searchshops_category_button)
    Button category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shops);

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
    public void onResetClick(){
        edittext.setText("");
    }

    // Map 버튼 이벤트
    @OnClick(R.id.searchshops_map_button)
    public void onMapClick(){
        Toast.makeText(SearchShopsActivity.this, "Map", Toast.LENGTH_SHORT).show(); // Test
    }

    // 카테고리 버튼 이벤트
    @OnClick(R.id.searchshops_category_button)
    public  void onCategoryClick(){
        category.setText("Category Test "+(int)(Math.random()*10)); // Test
    }

    // EditText에서 Search Action 발생 시, 장소를 검색하는 Method.
    public void SearchShops(){
        String searchText = edittext.getText().toString();

        // 키보드 내리기
        edittext.clearFocus();
        InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(edittext.getWindowToken(), 0);

        shopsAdapter.clearList();  // 검색 후 List 수정

        // Test code starts
        for(int i = 0; i < searchText.length(); i++){
            Shop tempShop = new Shop(searchText + i, "음식", "매장 위치"+i, "00 : 00 ~ 23 : 59", "012-3456-7890");
            shopsAdapter.addItem(tempShop);
        }
        // Test code ends
    }
}
