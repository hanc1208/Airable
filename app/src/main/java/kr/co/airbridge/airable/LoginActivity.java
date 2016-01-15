package kr.co.airbridge.airable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.utility.ActivityUtility;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_user_email)
    EditText userEmail;
    @Bind(R.id.login_user_password)
    EditText userPassword;

    LoginButton facebookLoginBtn;
    CallbackManager callbackManager;
    SharedPreferences pref;//facebook 로그인 callback
    SharedPreferences.Editor editor;
    RetrofitServer networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_key);
        toolbar.setTitleTextColor(0xff40C4FF);
        toolbar.setBackgroundColor(0x00000000);

        networkManager = new RetrofitServer();

        pref = getSharedPreferences("airable", MODE_PRIVATE);
        editor = pref.edit();

        facebookLoginBtn = (LoginButton) findViewById(R.id.login_facebook_btn);
        facebookLoginBtn.setReadPermissions("user_friends");
        facebookLoginBtn.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        editor.putString("login", "login");
                        editor.apply();
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        // Application code
                                        try {

                                            String id = (String) response.getJSONObject().get("id");//페이스북 아이디값
                                            String name = (String) response.getJSONObject().get("name");//페이스북 이름
                                            String email = (String) response.getJSONObject().get("email");//이메일
                                            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                                            Intent startActivityIntent = new Intent(getApplicationContext(), StartActivity.class);
                                            startActivityIntent.putExtra("username", name);
                                            startActivity(startActivityIntent);

                                        } catch (JSONException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }

                        @Override
                        public void onCancel () {

                        }

                        @Override
                        public void onError (FacebookException exception){

                        }
                    }

                    );
                }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    public void onEmailLoginClick(View view) {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        networkManager.postLogin(email, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                editor.putString("login", "login");
                Intent startActivityIntent = new Intent(getApplicationContext(), StartActivity.class);
                startActivityIntent.putExtra("username", response.body().toString());
                editor.putString("username", response.body().toString());
                editor.apply();
                startActivity(startActivityIntent);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
