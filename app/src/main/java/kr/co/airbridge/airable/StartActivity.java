package kr.co.airbridge.airable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class StartActivity extends AppCompatActivity {
    private String userName;

    @Bind(R.id.start_user_name)
    TextView userNameView1;

    @Bind(R.id.start_user_name2)
    TextView userNameView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent loginIntent = getIntent();
        userName = loginIntent.getStringExtra("username");

        ButterKnife.bind(this);
        userNameView1.setText(userName);
        userNameView2.setText(userName);

    }
    //티켓 입력 버튼
    public void onInputTicketClick(View view) {

        Intent intent = new Intent(getApplicationContext(),
                SearchAirInfoActivity.class
        );
        startActivity(intent);

    }
    //건너뛰기 버튼
    public void onJumpClick(View view) {

    }

}