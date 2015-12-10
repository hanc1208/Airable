package kr.co.airbridge.airable;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class AddTravelOptionsActivity extends AppCompatActivity {
    @Bind(R.id.grid_add_options)
    GridView gridOptions;

    @Bind(R.id.next_btn)
    ImageView nextBtn;

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
            , "병무 신고", "문화재", "부가세 환급", "자동출입국 심사등록"}; //cnt = 9

    public int[] cellState = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //선택은 1 아니면 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_options);

        ButterKnife.bind(this);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();
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
               //To do
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
                imageView.setImageResource(cellImage[position]);
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

