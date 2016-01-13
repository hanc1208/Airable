package kr.co.airbridge.airable;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.model.*;
import kr.co.airbridge.airable.model.Process;
import kr.co.airbridge.airable.utility.CircularProgressBar;
import kr.co.airbridge.airable.utility.DBManager;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main_circularprogress)
    CircularProgressBar timeCircleProgress;

    @Bind(R.id.flight_process_list)
    RecyclerView processListView;

    @Bind(R.id.main_process_grid)
    GridView mainProcessGrid;

    @Bind(R.id.circle_lefttime)
    TextView leftTimeTextView;

    @Bind(R.id.main_expectedtime)
    TextView expectedTimeView;

    private ProcessGridAdapter gridAdapter;
    private RecyclerAdapter listAdapter;
    private List<RecycleItem> items;
    private LinearLayoutManager layoutManager;
    private android.support.v7.widget.Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String leftTime = "2h 31min";
    private Handler uiHandler;
    private DBManager dbManager;
    private ArrayList<Process> processList;
    private int expectedTime;

    final int LEFTTIME_TEXTVIEW_CHANGE = 0;
    final int LEFTTIME_PROGRESS_CHANGE = 1;
    final int PROCESS_CHANGE = 3;
    final int BOARDINGTIME_REQUESTCODE = 100;

    int[] cellImage1 = {R.drawable.airport_1, R.drawable.ic_2, R.drawable.ic_3, R.drawable.ic_1,
            R.drawable.ic_10, R.drawable.ic_4, R.drawable.ic_7, R.drawable.check_in_1, R.drawable.luggage_1,
            R.drawable.customs_1, R.drawable.ic_11, R.drawable.ic_6, R.drawable.ic_8, R.drawable.ic_9,
            R.drawable.security_1, R.drawable.screening_1, R.drawable.ic_13, R.drawable.ic_12, R.drawable.boarding_1};
    int[] cellImage2 = {R.drawable.airport_2, R.drawable.ic_2_1, R.drawable.ic_3_1, R.drawable.ic_1_1,
            R.drawable.ic_10_1, R.drawable.ic_4_1, R.drawable.ic_7_1, R.drawable.check_in_2, R.drawable.luggage_2,
            R.drawable.customs_2, R.drawable.ic_11_1, R.drawable.ic_6_1, R.drawable.ic_8_1, R.drawable.ic_9_1,
            R.drawable.security_2, R.drawable.screening_2, R.drawable.ic_13_1, R.drawable.ic_12_1, R.drawable.boarding_2};
    int[] movingTime = {10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.anim_toolbar);
        toolbar.setTitle(leftTime);
        setSupportActionBar(toolbar);
        timeCircleProgress.setProgress(35);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        processListView.setHasFixedSize(true);
        processListView.setLayoutManager(layoutManager);
        dbManager = new DBManager(this);
        processList = dbManager.getProcessList();
        items = new ArrayList<>();

        changeProcessList(processList);

        listAdapter = new RecyclerAdapter(this, items);
        listAdapter.setHasStableIds(true);
        gridAdapter = new ProcessGridAdapter(this, items, cellImage1, cellImage2, movingTime);
        processListView.setClickable(false);
        processListView.setAdapter(listAdapter);
        mainProcessGrid.setAdapter(gridAdapter);

        uiHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg != null) {
                    switch (msg.what) {
                        case LEFTTIME_TEXTVIEW_CHANGE:
                            String leftTimeStr = (String) msg.obj;
                            leftTimeTextView.setText("2h 31min");
                            break;
                        case LEFTTIME_PROGRESS_CHANGE:
                            break;
                        case PROCESS_CHANGE:
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    processList.clear();
                                    processList = dbManager.getProcessList();
                                    items.clear();
                                    items = changeProcessList(processList);
                                    listAdapter = new RecyclerAdapter(getApplicationContext(), items);
                                    processListView.setAdapter(listAdapter);
                                    gridAdapter = new ProcessGridAdapter(getApplicationContext(), items, cellImage1, cellImage2, movingTime);
                                    mainProcessGrid.setAdapter(gridAdapter);
                                    int time = expectedTime/60;
                                    int minute = expectedTime%60;
                                    //expectedTimeView.setText("예상 소요시간은 " + time + "시간 " + minute + "분");

                                }
                            });
                            break;
                        default:
                            break;
                    }
                }
            }

        };
    }

    public List<RecycleItem> changeProcessList(ArrayList<Process> processList) {
        items = new ArrayList<RecycleItem>();
        for (int i = 0; i < processList.size(); i++) {
            RecycleItem item = new RecycleItem();
            Process process = processList.get(i);
            expectedTime  = 0;

            if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS) {
                item.setTitle(process.getName());
                item.setContent(process.getDescription());
                item.setPlace(process.getPlaceName());
                item.setTime(process.getTime());
                item.setState(process.getState());
                item.setProcessNum(process.getNo());
                item.setVerexid(process.getVertexid());
                expectedTime+= item.getTime();

                items.add(item);
            }
        }

        return items;
    }

    @OnClick(R.id.floating_button)
    public void onFloatingButtonClick() {
        Intent optionIntent = new Intent(this, AddTravelOptionsActivity.class);
        optionIntent.putExtra("requestCode", 1);
        startActivityForResult(optionIntent, BOARDINGTIME_REQUESTCODE, null);
    }

    @OnClick(R.id.main_flight)
    public void onMainFlightButtonClick() {
        Intent myAirActivityIntent = new Intent(getApplicationContext(), MyAirActivity.class);
        //편명정보 보내기
        startActivity(myAirActivityIntent);
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        Context context;
        List<RecycleItem> items;

        public RecyclerAdapter(Context context, List<RecycleItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_process_list, null);
            return new ViewHolder(v);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final RecycleItem item = items.get(position);

            String pos = Integer.toString(position + 1);
            holder.cardNum.setText(pos);
            holder.cardTitle.setText(item.getTitle());
            holder.cardNum.setBackgroundResource(R.drawable.card_num_bg);
            holder.titleLinear.setBackgroundResource(R.drawable.card_blue_bg1);
            holder.contentsLinear.setBackgroundResource(R.drawable.card_blue_bg2);
            holder.cardLine.setBackgroundColor(Color.parseColor("#263238"));
            holder.cardContent.setTextColor(Color.parseColor("#263238"));
            holder.cardPlace.setTextColor(Color.parseColor("#263238"));
            holder.cardSpotIcon.setVisibility(View.VISIBLE);
            holder.cardMapButton.setVisibility(View.VISIBLE);
            holder.cardContent.setText(item.getContent());
            holder.cardPlace.setText(item.getPlace());
            holder.cardMovingtime.setText(Integer.toString(movingTime[item.getProcessNum()-1]) + "분");
            holder.cardNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbManager.changeProcessState(item.getProcessNum(), Process.PASSED_PROCESS);
                    Message stateChangeMsg = uiHandler.obtainMessage();
                    stateChangeMsg.what = PROCESS_CHANGE;
                    uiHandler.sendMessage(stateChangeMsg);
                }
            });
            if (item.getState() == Process.PASSED_PROCESS) {
                holder.cardNum.setText("");
                holder.cardNum.setBackgroundResource(R.drawable.check_ic);
                holder.titleLinear.setBackgroundResource(R.drawable.card_bg1);
                holder.contentsLinear.setBackgroundResource(R.drawable.card_bg2);
                holder.cardLine.setBackgroundColor(Color.parseColor("#eceff1"));
                holder.cardPlace.setTextColor(Color.parseColor("#eceff1"));
                holder.cardContent.setTextColor(Color.parseColor("#eceff1"));
                holder.cardSpotIcon.setVisibility(View.INVISIBLE);
                holder.cardMapButton.setVisibility(View.GONE);
                holder.cardMovingtime.setTextColor(Color.parseColor("#eceff1"));
            }
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView cardNum;
            TextView cardTitle;
            TextView cardContent;
            TextView cardPlace;
            TextView cardMovingtime;
            ImageView cardMapButton;
            ImageView cardSpotIcon;
            CardView cardView;
            LinearLayout titleLinear;
            LinearLayout contentsLinear;
            View cardLine;

            public ViewHolder(View itemView) {
                super(itemView);
                cardNum = (TextView) itemView.findViewById(R.id.process_card_num);
                cardTitle = (TextView) itemView.findViewById(R.id.card_title);
                cardContent = (TextView) itemView.findViewById(R.id.card_contents);
                cardPlace = (TextView) itemView.findViewById(R.id.card_place);
                cardMapButton = (ImageView) itemView.findViewById(R.id.card_arrow);
                cardSpotIcon = (ImageView) itemView.findViewById(R.id.card_spot);
                cardView = (CardView) itemView.findViewById(R.id.cardview);
                titleLinear = (LinearLayout) itemView.findViewById(R.id.card_title_bg);
                contentsLinear = (LinearLayout) itemView.findViewById(R.id.card_contents_bg);
                cardLine = (View) itemView.findViewById(R.id.card_line);
                cardMovingtime = (TextView) itemView.findViewById(R.id.card_movingtime);
            }
        }
    }

    class ProcessGridAdapter extends BaseAdapter {
        private Context context;
        private List<RecycleItem> items;
        private int[] cellImage1;
        private int[] cellImage2;
        private int[] movingTime;

        public ProcessGridAdapter(Context context, List<RecycleItem> items, int[] cellImage1, int[] cellImage2, int[] movingTime) {
            this.context = context;
            this.items = items;
            this.cellImage1 = cellImage1;
            this.cellImage2 = cellImage2;
            this.movingTime = movingTime;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            RecycleItem item = items.get(position);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_main_process_grid, null);
                TextView processName = (TextView) convertView.findViewById(R.id.main_process_grid_text);
                ImageView processLogo = (ImageView) convertView.findViewById(R.id.main_process_grid_image);
                TextView processTime = (TextView) convertView.findViewById(R.id.main_process_grid_time);
                TextView processMovingTime = (TextView) convertView.findViewById(R.id.main_process_grid_movingtime);
                ImageView processFlow = (ImageView) convertView.findViewById(R.id.main_process_grid_arrow);

                if (item.getState() == Process.INCLUDE_PROCESS) {
                    processLogo.setImageResource(cellImage1[item.getProcessNum() - 1]);
                    processFlow.setImageResource(R.drawable.flow_1);
                    processName.setText(item.getTitle());
                    processName.setTextColor(Color.parseColor("#575757"));
                    processTime.setText(Integer.toString(item.getTime()) + "분");
                    processTime.setTextColor(Color.parseColor("#575757"));
                    processMovingTime.setText(Integer.toString(movingTime[position])+"분");
                    expectedTime+= movingTime[position];
                    processMovingTime.setTextColor(Color.parseColor("#575757"));
                } else if (item.getState() == Process.PASSED_PROCESS) {
                    processLogo.setImageResource(cellImage2[item.getProcessNum() - 1]);
                    processFlow.setImageResource(R.drawable.flow_2);
                    processName.setText(item.getTitle());
                    processName.setTextColor(Color.parseColor("#eceff1"));
                    processTime.setText(Integer.toString(item.getTime()) + "분");
                    processTime.setTextColor(Color.parseColor("#eceff1"));
                    processMovingTime.setText(Integer.toString(movingTime[position])+"분");
                    processMovingTime.setTextColor(Color.parseColor("#eceff1"));
                }

                if (position == items.size() - 1) {
                    convertView.findViewById(R.id.main_process_grid_arrow).setVisibility(View.GONE);
                    convertView.findViewById(R.id.main_process_grid_movingtime).setVisibility(View.GONE);
                }
            }

            return convertView;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case BOARDINGTIME_REQUESTCODE:
                if (resultCode == RESULT_OK) {
                    final long boardingTimeMills = (long) data.getExtras().getLong("boardingTime");
                    Timer leftTimer = new Timer();
                    TimerTask leftTimeCalculator = new TimerTask() {
                        Date leftTimeDate;
                        String leftTime;

                        @Override
                        public void run() {
                            Date currentTime = new Date(System.currentTimeMillis());
                            long currentTimeMills = currentTime.getTime();
                            long leftTimeMils = boardingTimeMills - currentTimeMills;
                            leftTimeDate = new Date(leftTimeMils);
                            leftTime = Integer.toString(leftTimeDate.getHours()) + "h "
                                    + Integer.toString(leftTimeDate.getMinutes()) + "mins"; //시간변환

                            Message leftTimeMsg = uiHandler.obtainMessage();
                            leftTimeMsg.what = LEFTTIME_TEXTVIEW_CHANGE;
                            leftTimeMsg.obj = leftTime;
                            uiHandler.sendMessage(leftTimeMsg);
                        }
                    };

                    Message processChangeMsg = uiHandler.obtainMessage();
                    processChangeMsg.what = PROCESS_CHANGE;
                    uiHandler.sendMessage(processChangeMsg);
                    leftTimer.schedule(leftTimeCalculator, 0, 3000);
                }
                break;
        }
    }
}
