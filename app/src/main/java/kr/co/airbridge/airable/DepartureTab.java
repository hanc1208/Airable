package kr.co.airbridge.airable;
// This project was created by Ferdousur Rahman Shajib
// www.androstock.com
// You use this project anytime, anywhere. There is no copywrite issue.

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;



import java.io.Serializable;

public class DepartureTab extends Fragment {
    Activity activity;
 
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.departure_tab,container,false);

        activity = getActivity();


        return v;
    }




}
