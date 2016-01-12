package kr.co.airbridge.airable;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class MapProcessActivity extends AppCompatActivity{
    @Bind(R.id.map_slide_viewpager)
    ViewPager pager;

    Fragment curFragment = new Fragment();
    int pageCount;
    ArrayList<MapProcess> processList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_slide);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.map_slide_toolbar);
        activityUtility.setNavigationAsBack();

        ButterKnife.bind(this);

        // processList 세팅
        processList = new ArrayList<MapProcess>();
        for (int i = 0; i < 4; i++)
            processList.add(new MapProcess("Title" + i, "Detail" + i, "Location" + i));
        pageCount = processList.size();

        // View pager 세팅
        pager.setAdapter(new adapter(getSupportFragmentManager()));
    }

    private class adapter extends FragmentPagerAdapter{
        public adapter(android.support.v4.app.FragmentManager fm){ super(fm); }

        @Override
        public Fragment getItem(int position){
            if(position >= 0 && position < processList.size()){
                MapProcess curProcess = processList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("title", curProcess.title);
                bundle.putString("detail", curProcess.detail);
                bundle.putString("location", curProcess.location);
                curFragment = new MapProcessSubpage();
                curFragment.setArguments(bundle);
            }
            return curFragment;
        }

        @Override
        public int getCount() { return pageCount; }
    }


    public class MapProcess{
        String title;
        String detail;
        String location;

        public MapProcess(String title, String detail, String location){
            this.title = title;
            this.detail = detail;
            this.location = location;
        }
    }
}
