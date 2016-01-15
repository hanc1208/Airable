package kr.co.airbridge.airable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.FacebookSdk;

public class SplashActivity extends Activity {
    SharedPreferences pref;
    String loginInfo;
    String haveFlightInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FacebookSdk.sdkInitialize(getApplicationContext());
        pref = getSharedPreferences("airable", MODE_PRIVATE);
        loginInfo = pref.getString("login", "logout");
        haveFlightInfo = pref.getString("haveFlightInfo", "null");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("logout".equals(loginInfo)) {
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                } else if ("login".equals(loginInfo)) {
                    if ("null".equals(haveFlightInfo)) {
                        String username = pref.getString("username", "noname");
                        Intent searchAirInfoIntent = new Intent(getApplicationContext(), SearchAirInfoActivity.class);
                        searchAirInfoIntent.putExtra("username", username);
                        startActivity(new Intent(SplashActivity.this, SearchAirInfoActivity.class));
                    } else if ("haveFlightInfo".equals(haveFlightInfo)) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }
                finish();
            }
        }, 2000);
    }
}

