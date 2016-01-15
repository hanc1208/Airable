package kr.co.airbridge.airable;

import android.app.FragmentManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import kr.co.airbridge.airable.model.*;
import kr.co.airbridge.airable.model.Process;
import kr.co.airbridge.airable.utility.ActivityUtility;
import kr.co.airbridge.airable.utility.DBManager;

public class SearchMapActivity extends AppCompatActivity implements FloorButtonListener, CurrentPositionReceiver.CurrentPositionListener{
    @Bind(R.id.search_map_floor_button)
    Button searchMapFloor;
    @Bind(R.id.search_map_viewpager)
    ViewPager pager;
    @Bind(R.id.search_map_search_button)
    ImageView searchButton;
    @Bind(R.id.search_map_map)
    MapView mapView;

    Bundle bundle;
    String searchKeyword;
    int pageCount = 0;
    int curFloor = -1;
    FragmentManager fragmentManager;
    int curPageNum = 0;
    ArrayList<Shop> permanentShopList;
    ArrayList<Shop> shopList;
    DBManager dbManager;
    Fragment curFragment;

    CurrentPositionReceiver currentPositionReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        fragmentManager = getFragmentManager();

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.search_map_toolbar);
        activityUtility.setNavigationAsBack();

        ButterKnife.bind(this);

        dbManager = new DBManager(this);
        permanentShopList = dbManager.getShopList();

        bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("searchkeyword")!=null) {
            searchKeyword = bundle.getString("searchkeyword");
            curFloor = bundle.getInt("curFloor");
        }else{
            searchKeyword = "";
            curFloor = -1;
        }
        setCurFloor(curFloor);
        Log.i("mytag", "Search Keyword : " + searchKeyword); // Intent getExtra test

        // Map setting
        Place place = Place.createTestPlace();
        Map map = place.getFloors()[0].getMap();
        try {
            mapView.setMap(map);
        } catch (SVGParseException | IOException e) {
            e.printStackTrace();
        }

//////////////////////
        List<Vertex> vertexList = new ArrayList<Vertex>();

        for(int i = 0; i < shopList.size(); i++){
            if(shopList.get(i).getVertexid() != -1)
                Collections.addAll(vertexList, Dijkstra.getShortestPath(map.getVertexes(), shopList.get(i).getVertexid(), shopList.get(i).getVertexid()));
        }//for
        Path path = new Path(vertexList, Color.TRANSPARENT, 0);


        for(int i = 0; i < shopList.size(); i++){
            Shop shop = shopList.get(i);
            if(shop.getVertexid() != -1){
                Drawable drawNormal = new MyDrawable(shop, i, false);
                Drawable drawNow = new MyDrawable(shop, i, true);
                if(i == curPageNum){
                    path.putMarker(shop.getVertexid(), drawNow);
                }else{
                    path.putMarker(shop.getVertexid(), drawNormal);
                }
            }
        }//for

        mapView.setPath(path);
//////////////////////
        currentPositionReceiver = new CurrentPositionReceiver(this, map, this);

        // Shop list
        updateShopList();

        // View pager 세팅
        pager.setAdapter(new adapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new PageListener());

        // Search button 비활성화
        searchButton.setVisibility(View.INVISIBLE);
    }

    public void updateShopList(){
        shopList = new ArrayList<Shop>();
        for(Shop s : permanentShopList){
            if(s.getInfo().contains(searchKeyword) || s.getTitle().contains(searchKeyword)){
                if(curFloor == -1){
                    shopList.add(s);
                }else if(s.getFloor() == curFloor){
                    shopList.add(s);
                }
            }
        }
        pageCount = shopList.size();

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
        updateShopList();
        searchMapFloor.setText(tempStr);
        curPageNum = 0;
        curFragment = null;
        pager.setAdapter(new adapter(getSupportFragmentManager()));
        pager.getAdapter().notifyDataSetChanged();
        if(curFloor == 40 || curFloor == 99)
            putCurMarker();
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
        if (currentPositionReceiver.isScanning()) {
            currentPositionReceiver.stopScan();
        } else {
            currentPositionReceiver.startScan();
        }
    }


    @OnClick(R.id.search_map_search_button)
    public void onSearchMapSearchClick(){
        Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show(); // Test
    }

    private class PageListener extends ViewPager.SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
            curPageNum = position;
            putCurMarker();
        }
    }

    private class adapter extends FragmentStatePagerAdapter {
        public adapter(android.support.v4.app.FragmentManager fm){ super(fm); }

        @Override
        public Fragment getItem(int position){
            if(position >= 0 && position < shopList.size()){
                Shop curShop = shopList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("title", curShop.getTitle());
                bundle.putString("time", curShop.getTime());
                bundle.putString("location", curShop.getLocation());
                bundle.putString("tel", curShop.getTel());
                curFragment = new SearchMapSubpage();
                curFragment.setArguments(bundle);
            }
            return curFragment;
        }

        @Override
        public int getCount() { return pageCount; }

        @Override
        public int getItemPosition(Object item) {
            return POSITION_NONE;
        }

    }


    @Override
    public void onReceive(Point currentPosition) {
        mapView.setCurrentPosition(currentPosition);
    }

    public void putCurMarker(){
        Path path = mapView.getPath();

        for(int i = 0; i < permanentShopList.size(); i++){
            Shop shop = permanentShopList.get(i);
            if(shop.getVertexid() != -1){
                Drawable drawNormal = new MyDrawable(shop, i, false);
                Drawable drawNow = new MyDrawable(shop, i, true);
                if(shopList.isEmpty() == false && shop == shopList.get(curPageNum)){
                    path.putMarker(shop.getVertexid(), drawNow);
                }else{
                    path.putMarker(shop.getVertexid(), drawNormal);
                }
            }
        }//for

        mapView.invalidate();
    }


    class MyDrawable extends Drawable {
        Shop shop;
        int i;
        Drawable drawable_marker = getResources().getDrawable(R.drawable.marker);
        Drawable drawable_marker_n = getResources().getDrawable(R.drawable.marker_n);
        boolean isCurrentMarker;

        public MyDrawable(Shop shop, int i, boolean isCurrentMarker) {
            this.shop = shop;
            this.i = i;
            this.isCurrentMarker = isCurrentMarker;
            drawable_marker.setBounds(0, 0, drawable_marker.getIntrinsicWidth(), drawable_marker.getIntrinsicHeight());
            drawable_marker_n.setBounds(0, 0, drawable_marker_n.getIntrinsicWidth(), drawable_marker_n.getIntrinsicHeight());
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
            if(isCurrentMarker == false){
                drawable_marker_n.draw(canvas);
            }else{
                drawable_marker.draw(canvas);
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
