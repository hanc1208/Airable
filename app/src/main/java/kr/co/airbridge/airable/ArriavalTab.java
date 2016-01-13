package kr.co.airbridge.airable;

// This project was created by Ferdousur Rahman Shajib
// www.androstock.com
// You use this project anytime, anywhere. There is no copywrite issue.

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArriavalTab extends Fragment {
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.arrival_tab,container,false);
        activity = getActivity();

        return v;
    }





}