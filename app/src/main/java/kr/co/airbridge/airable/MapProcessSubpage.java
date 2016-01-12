package kr.co.airbridge.airable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapProcessSubpage extends Fragment{
    MapProcessActivity.MapProcess mapProcess;

    public MapProcessSubpage(){ }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.map_slide_childview,container,false);;

        TextView titleTextview = (TextView)linearLayout.findViewById(R.id.map_slide_childview_title_textview);
        TextView detailTextview = (TextView)linearLayout.findViewById(R.id.map_slide_childview_detail_textview);
        TextView locationTextview = (TextView)linearLayout.findViewById(R.id.map_slide_childview_location_textview);

        Bundle bundle = getArguments();
        titleTextview.setText(bundle.getString("title"));
        detailTextview.setText(bundle.getString("detail"));
        locationTextview.setText(bundle.getString("location"));

        return linearLayout;
    }

}
