package kr.co.airbridge.airable;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloorFragment extends Fragment {
    @Bind(R.id.floor_select_left_b1f)
    Button leftb1f;
    @Bind(R.id.floor_select_left_1f)
    Button left1f;
    @Bind(R.id.floor_select_left_2f)
    Button left2f;
    @Bind(R.id.floor_select_left_3f)
    Button left3f;
    @Bind(R.id.floor_select_left_4f)
    Button left4f;

    @Bind(R.id.floor_select_mid_b1f)
    Button midb1f;
    @Bind(R.id.floor_select_mid_1f)
    Button mid1f;
    @Bind(R.id.floor_select_mid_2f)
    Button mid2f;
    @Bind(R.id.floor_select_mid_3f)
    Button mid3f;
    @Bind(R.id.floor_select_mid_4f)
    Button mid4f;

    @Bind(R.id.floor_select_right_b1f)
    Button rightb1f;
    @Bind(R.id.floor_select_right_1f)
    Button right1f;
    @Bind(R.id.floor_select_right_2f)
    Button right2f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.floor_select, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        int curFloor = bundle.getInt("curfloor");

        Button targetButton;
        switch(curFloor){
            case 10:
                targetButton = leftb1f;
                break;
            case 11:
                targetButton = left1f;
                break;
            case 12:
                targetButton = left2f;
                break;
            case 13:
                targetButton = left3f;
                break;
            case 14:
                targetButton = left4f;
                break;
            case 20:
                targetButton = midb1f;
                break;
            case 21:
                targetButton = mid1f;
                break;
            case 22:
                targetButton = mid2f;
                break;
            case 23:
                targetButton = mid3f;
                break;
            case 24:
                targetButton = mid4f;
                break;
            case 30:
                targetButton = rightb1f;
                break;
            case 31:
                targetButton = right1f;
                break;
            case 32:
                targetButton = right2f;
                break;
            default:
                targetButton = null;
                break;
        }
        if(targetButton != null) targetButton.setBackgroundResource(R.drawable.position_2_withwhite);

        return view;
    }

    public void floorButtonClick(View view){
        FloorButtonListener curAct = (FloorButtonListener)getActivity();

        switch (view.getId()){
            case R.id.floor_select_left_b1f:
                curAct.setCurFloor(10);
                break;
            case R.id.floor_select_left_1f:
                curAct.setCurFloor(11);
                break;
            case R.id.floor_select_left_2f:
                curAct.setCurFloor(12);
                break;
            case R.id.floor_select_left_3f:
                curAct.setCurFloor(13);
                break;
            case R.id.floor_select_left_4f:
                curAct.setCurFloor(14);
                break;
            case R.id.floor_select_mid_b1f:
                curAct.setCurFloor(20);
                break;
            case R.id.floor_select_mid_1f:
                curAct.setCurFloor(21);
                break;
            case R.id.floor_select_mid_2f:
                curAct.setCurFloor(22);
                break;
            case R.id.floor_select_mid_3f:
                curAct.setCurFloor(23);
                break;
            case R.id.floor_select_mid_4f:
                curAct.setCurFloor(24);
                break;
            case R.id.floor_select_right_b1f:
                curAct.setCurFloor(30);
                break;
            case R.id.floor_select_right_1f:
                curAct.setCurFloor(31);
                break;
            case R.id.floor_select_right_2f:
                curAct.setCurFloor(32);
                break;
            case R.id.floor_select_all_button:
                curAct.setCurFloor(-1);
                break;
        }

        FragmentManager curFragMan = getActivity().getFragmentManager();
        curFragMan.beginTransaction()
                .remove(curFragMan.findFragmentByTag("FLOOR_FRAGMENT"))
                .commit();
    }

    @OnClick(R.id.floor_select_close_button)
    public void onFloorSelectCloseClick(){
        FragmentManager curFragMan = getActivity().getFragmentManager();
        curFragMan.beginTransaction()
                .remove(curFragMan.findFragmentByTag("FLOOR_FRAGMENT"))
                .commit();
    }

    @OnClick(R.id.floor_select_curpoint_button)
    public void onFloorSelectCurpointClick(){

    }
}
