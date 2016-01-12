package kr.co.airbridge.airable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;
import kr.co.airbridge.airable.utility.DBManager;
import kr.co.airbridge.airable.model.Process;

public class MapProcessActivity extends AppCompatActivity{
    @Bind(R.id.map_slide_viewpager)
    ViewPager pager;
    @Bind(R.id.map_slide_timer_textview)
    TextView toolbarTextview;

    Fragment curFragment = new Fragment();
    int pageCount;
    int curPageNum = 0;
    ArrayList<Process> processList;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_slide);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.map_slide_toolbar);
        activityUtility.setNavigationAsBack();

        ButterKnife.bind(this);

        // DB Setting
        dbManager = new DBManager(this);


        // processList 세팅
        processList = new ArrayList<Process>();
        ArrayList<Process> tempArr = dbManager.getProcessList();
        for(Process tempPrc : tempArr){
            if(tempPrc.getState() != -1)
                processList.add(tempPrc);
        }
        pageCount = processList.size();

        // View pager 세팅
        pager.setAdapter(new adapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new PageListener());

        // Toolbar에 있는 시간 바꾸기
        toolbarTextview.setText("2h 32min"); // Test
    }

    @OnClick(R.id.map_slide_search_button)
    public void onMapProcessSearchClick(){
        Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.map_slide_current_point_button)
    public void onMapProcessCurrentPointClick(){
        Toast.makeText(getApplicationContext(), "Current Point", Toast.LENGTH_SHORT).show();
    }

    private class PageListener extends ViewPager.SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
            curPageNum = position;
            Toast.makeText(getApplicationContext(), "Page number "+position,Toast.LENGTH_SHORT).show(); // Test
        }
    }

    private class adapter extends FragmentPagerAdapter{
        public adapter(android.support.v4.app.FragmentManager fm){ super(fm); }

        @Override
        public Fragment getItem(int position){
            if(position >= 0 && position < processList.size()){
                Process curProcess = processList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("title", curProcess.getName());
                bundle.putString("detail", curProcess.getDescription());
                bundle.putString("location", curProcess.getPlaceName());
                curFragment = new MapProcessSubpage();
                curFragment.setArguments(bundle);
            }
            return curFragment;
        }

        @Override
        public int getCount() { return pageCount; }
    }

}
