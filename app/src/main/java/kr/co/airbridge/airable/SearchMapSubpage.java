package kr.co.airbridge.airable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchMapSubpage extends Fragment {
    public SearchMapSubpage() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.search_map_childview, container, false);

        TextView titleTextview = (TextView) linearLayout.findViewById(R.id.search_map_curshop_title_textview);
        TextView timeTextview = (TextView) linearLayout.findViewById(R.id.search_map_curshop_time_textview);
        TextView locationTextview = (TextView) linearLayout.findViewById(R.id.search_map_curshop_location_textview);
        ImageButton callButton = (ImageButton) linearLayout.findViewById(R.id.search_map_curshop_call);
        ImageButton markButton = (ImageButton) linearLayout.findViewById(R.id.search_map_curshop_mark);

        Bundle bundle = getArguments();
        titleTextview.setText(bundle.getString("title"));
        timeTextview.setText(bundle.getString("detail"));
        locationTextview.setText(bundle.getString("location"));
        final String tel = bundle.getString("tel");

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel)); // Test
                startActivity(intent);
            }
        });
        markButton.setVisibility(View.INVISIBLE);

        return linearLayout;
    }
}