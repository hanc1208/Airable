package kr.co.airbridge.airable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class SearchMapActivity extends AppCompatActivity{
    Bundle bundle;
    String searchKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.search_map_toolbar);
        activityUtility.setNavigationAsBack();

        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        if(bundle.getString("searchkeyword")!=null) {
            searchKeyword = bundle.getString("searchkeyword");
        }else{
            searchKeyword = "";
        }
        Log.i("mytag", "Search Keyword : " + searchKeyword); // Intent getExtra test
    }


    @OnClick(R.id.search_map_floor_button)
    public void onSearchMapFloorClick(){
        Toast.makeText(getApplicationContext(), "Map", Toast.LENGTH_SHORT).show(); // Test
    }

    @OnClick(R.id.search_map_current_point_button)
    public void onSearchMapCurPointClick(){
        Toast.makeText(getApplicationContext(), "Current Point", Toast.LENGTH_SHORT).show(); // Test
    }

    @OnClick(R.id.search_map_search_button)
    public void onSearchMapSearchClick(){
        Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show(); // Test
    }

    @OnClick(R.id.search_map_curshop_mark)
    public void onCurshopMarkClick(){
        Toast.makeText(getApplicationContext(), "Mark", Toast.LENGTH_SHORT).show(); // Test
    }

    @OnClick(R.id.search_map_curshop_call)
    public void onCurshopCallClick(){
        Toast.makeText(getApplicationContext(), "Call", Toast.LENGTH_SHORT).show(); // Test
    }
}
