package kr.co.airbridge.airable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        ///// image 생성 코드
        final Paint pntWhite = new Paint();
        pntWhite.setAntiAlias(true);
        pntWhite.setColor(Color.WHITE);

        for(int i = 0; i < processList.size(); i++){
            Process prc = processList.get(i);
            Drawable tempDraw = new MyDrawable(prc, i, pntWhite);

        }//for
        ///////////////////////////

        // View pager 세팅
        pager.setAdapter(new adapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new PageListener());

        // Toolbar에 있는 시간 바꾸기
        toolbarTextview.setText("time"); // Test
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

    class MyDrawable extends Drawable {
        Process prc;
        int i;
        Paint pnt;
        Drawable drawable_map_1 = getResources().getDrawable(R.drawable.map_1);
        Drawable drawable_map_check = getResources().getDrawable(R.drawable.map_check);

        public MyDrawable(Process prc, int i, Paint pnt) {
            this.prc = prc;
            this.i = i;
            this.pnt = pnt;
            drawable_map_1.setBounds(0, 0, drawable_map_1.getIntrinsicWidth(), drawable_map_1.getIntrinsicHeight());
            drawable_map_check.setBounds(0, 0, drawable_map_check.getIntrinsicWidth(), drawable_map_check.getIntrinsicHeight());
        }

        @Override
        public void draw(Canvas canvas) {
            switch (prc.getState()){
                case 0:
                    drawable_map_1.draw(canvas);
                    canvas.drawText(Integer.toString(i), 0, 10, pnt);
                    break;
                case 1:
                    drawable_map_check.draw(canvas);
                    break;
            }
        }
        @Override
        public void setAlpha(int alpha) {

        }
        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }
        @Override
        public int getOpacity() {
            return 1;
        }
    }

}
