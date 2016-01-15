package kr.co.airbridge.airable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.model.Process;
import kr.co.airbridge.airable.utility.ActivityUtility;
import kr.co.airbridge.airable.utility.DBManager;

public class AddTravelOptionsActivity extends AppCompatActivity {
    @Bind(R.id.grid_add_options)
    GridView gridOptions;

    @Bind(R.id.next_btn)
    ImageView nextBtn;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int[] cellImage = {R.drawable.change,
            R.drawable.roaming,
            R.drawable.insurance,
            R.drawable.customs,
            R.drawable.quarantine,
            R.drawable.military,
            R.drawable.cultural,
            R.drawable.refund,
            R.drawable.auto};
    String[] cellText = {"환전", "로밍", "여행자 보험", "세관 신고", "검역 신고"
            , "병무 신고", "문화재", "부가세 환급", "자동출입국 심사등록"};

    public int[] cellState = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //선택은 1 아니면 0
    public int[] previousCellState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private Context context;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_options);

        ButterKnife.bind(this);
        context = this;

        pref = getSharedPreferences("airable", MODE_PRIVATE);
        editor = pref.edit();

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();
        Toolbar toolbar  = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_key);
        toolbar.setTitleTextColor(0xff40C4FF);
        toolbar.setBackgroundColor(0x00000000);

        dbManager = new DBManager(context);
        ArrayList<Process> processList = dbManager.getProcessList();
        for (int i = 0; i < processList.size(); i++) {
            Process process = processList.get(i);
            switch (process.getNo()) {
                case 4:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[0] = 1;
                    break;
                case 2:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[1] = 1;
                    break;
                case 3:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[2] = 1;
                    break;
                case 10:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[3] = 1;
                    break;
                case 6:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[4] = 1;
                    break;
                case 12:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[5] = 1;
                    break;
                case 7:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[6] = 1;
                    break;
                case 13:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[7] = 1;
                    break;
                case 14:
                    if (process.getState() == Process.INCLUDE_PROCESS || process.getState() == Process.PASSED_PROCESS)
                        previousCellState[8] = 1;
                    break;
            }
        }
        GridAdapter gridAdapter = new GridAdapter(this, cellText, cellImage);

        gridOptions.setAdapter(gridAdapter);
        gridOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view.findViewById(R.id.cell_image);
                if (cellState[position] == 0) {
                    cellState[position] = 1;
                    imageView.setImageResource(R.drawable.check);
                } else if (cellState[position] == 1) {
                    cellState[position] = 0;
                    imageView.setImageResource(cellImage[position]);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent boardingInfoIntent = getIntent();
                Calendar cal = Calendar.getInstance();

                Date boardingDate = new Date(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE),00,10);
                boardingInfoIntent.putExtra("boardingTime", boardingDate.getTime());
                setResult(RESULT_OK, boardingInfoIntent);

                for (int i = 0; i < cellState.length; i++) {
                    if (cellState[i] == 1) {
                        switch (i) {
                            case 0: //4/2/3/10/6/12/8/13/14
                                dbManager.changeProcessState(4, Process.INCLUDE_PROCESS);
                                break;
                            case 1:
                                dbManager.changeProcessState(2, Process.INCLUDE_PROCESS);
                                break;
                            case 2:
                                dbManager.changeProcessState(3, Process.INCLUDE_PROCESS);
                                break;
                            case 3:
                                dbManager.changeProcessState(10, Process.INCLUDE_PROCESS);
                                break;
                            case 4:
                                dbManager.changeProcessState(6, Process.INCLUDE_PROCESS);
                                break;
                            case 5:
                                dbManager.changeProcessState(12, Process.INCLUDE_PROCESS);
                                break;
                            case 6:
                                dbManager.changeProcessState(7, Process.INCLUDE_PROCESS);
                                break;
                            case 7:
                                dbManager.changeProcessState(13, Process.INCLUDE_PROCESS);
                                break;
                            case 8:
                                dbManager.changeProcessState(14, Process.INCLUDE_PROCESS);
                                break;
                        }
                    }
                }

                int requestCode = boardingInfoIntent.getIntExtra("requestCode", -1);
                editor.putString("haveFlightInfo", "haveFlightInfo");
                editor.apply();

                if (requestCode == 0) {
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else if (requestCode == 1) {
                    finish();
                }
            }
        });
    }

    class GridAdapter extends BaseAdapter {
        private Context context;
        private String[] cellText;
        private int[] cellImage;

        public GridAdapter(Context context, String[] cellText, int[] cellImage) {
            this.context = context;
            this.cellText = cellText;
            this.cellImage = cellImage;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_travel_options_grid, null);
                TextView textView = (TextView) convertView.findViewById(R.id.cell_text);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.cell_image);
                textView.setText(cellText[position]);
                if(previousCellState[position] == 1) {
                    imageView.setImageResource(R.drawable.check);
                } else {
                    imageView.setImageResource(cellImage[position]);
                }
            }

            return convertView;
        }

        @Override
        public int getCount() {
            return cellText.length;
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

}

