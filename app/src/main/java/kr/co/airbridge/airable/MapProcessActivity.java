package kr.co.airbridge.airable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
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

import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.map.CurrentPositionReceiver;
import kr.co.airbridge.airable.map.Dijkstra;
import kr.co.airbridge.airable.map.Map;
import kr.co.airbridge.airable.map.MapView;
import kr.co.airbridge.airable.map.Path;
import kr.co.airbridge.airable.map.Vertex;
import kr.co.airbridge.airable.model.Place;
import kr.co.airbridge.airable.utility.ActivityUtility;
import kr.co.airbridge.airable.utility.DBManager;
import kr.co.airbridge.airable.model.Process;

public class MapProcessActivity extends AppCompatActivity implements CurrentPositionReceiver.CurrentPositionListener {
    @Bind(R.id.map_slide_viewpager)
    ViewPager pager;
    @Bind(R.id.map_slide_timer_textview)
    TextView toolbarTextview;
    @Bind(R.id.map_slide_map)
    MapView mapView;

    Fragment curFragment = new Fragment();
    int pageCount;
    int curPageNum = 0;
    ArrayList<Process> processList;
    DBManager dbManager;

    CurrentPositionReceiver currentPositionReceiver;

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

        Place place = Place.createTestPlace();
        Map map = place.getFloors()[0].getMap();
        try {
            mapView.setMap(map);
        } catch (SVGParseException | IOException e) {
            e.printStackTrace();
        }

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
        pntWhite.setColor(getResources().getColor(R.color.colorPrimaryDark));
        List<Vertex> vertexList = new ArrayList<Vertex>();

        for(int i = 0; i < processList.size(); i++){
            if(i!=0){
                Collections.addAll(vertexList, Dijkstra.getShortestPath(map.getVertexes(), processList.get(i - 1).getVertexid(), processList.get(i).getVertexid()));
            }
        }//for
        Path path = new Path(vertexList, getResources().getColor(R.color.colorPrimary), 10);
        for(int i = 0; i < processList.size(); i++){
            Process prc = processList.get(i);
            Drawable tempDraw = new MyDrawable(prc, i, pntWhite);
            path.putMarker(prc.getVertexid(), tempDraw);
        }//for
        mapView.setPath(path);

        currentPositionReceiver = new CurrentPositionReceiver(this, map, this);
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
        if (currentPositionReceiver.isScanning()) {
            currentPositionReceiver.stopScan();
        } else {
            currentPositionReceiver.startScan();
        }
    }

    @Override
    public void onReceive(Point currentPosition) {
        mapView.setCurrentPosition(currentPosition);
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
        public int getIntrinsicWidth() {
            return 50;
        }

        @Override
        public int getIntrinsicHeight() {
            return 50;
        }

        @Override
        public void draw(Canvas canvas) {
            switch (prc.getState()){
                case 0:
                    drawable_map_1.draw(canvas);
                    pnt.setTextSize(40.0f);
                    canvas.drawText(Integer.toString(i+1), 20, 45, pnt);
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
