package kr.co.airbridge.airable;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class SearchMapActivity extends AppCompatActivity implements FloorButtonListener{
    @Bind(R.id.search_map_floor_button)
    Button searchMapFloor;

    Bundle bundle;
    String searchKeyword;
    int curFloor = -1;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        fragmentManager = getFragmentManager();

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.search_map_toolbar);
        activityUtility.setNavigationAsBack();

        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        if(bundle.getString("searchkeyword")!=null) {
            searchKeyword = bundle.getString("searchkeyword");
            curFloor = bundle.getInt("curFloor");
        }else{
            searchKeyword = "";
            curFloor = -1;
        }
        setCurFloor(curFloor);
        Log.i("mytag", "Search Keyword : " + searchKeyword); // Intent getExtra test
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
                tempStr = "B1F";
                break;
            case 11:
                tempStr = "1F";
                break;
            case 12:
                tempStr = "2F";
                break;
            case 13:
                tempStr = "3F";
                break;
            case 14:
                tempStr = "4F";
                break;
            case 20:
                tempStr = "B1F";
                break;
            case 21:
                tempStr = "1F";
                break;
            case 22:
                tempStr = "2F";
                break;
            case 23:
                tempStr = "3F";
                break;
            case 24:
                tempStr = "4F";
                break;
            case 30:
                tempStr = "B1F";
                break;
            case 31:
                tempStr = "1F";
                break;
            case 32:
                tempStr = "2F";
                break;
            case 33:
                tempStr = "3F";
                break;
            case 40:
                tempStr = "sGen";
                break;
            default:
                tempStr = "ALL";
                break;
        }
        searchMapFloor.setText(tempStr);
    }

    @OnClick(R.id.search_map_floor_button)
    public void onSearchMapFloorClick(){
        FloorFragment floorFragment = new FloorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("curfloor", curFloor);
        floorFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .add(R.id.search_map_fragment_container, floorFragment, "FLOOR_FRAGMENT")
                .commit();
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
