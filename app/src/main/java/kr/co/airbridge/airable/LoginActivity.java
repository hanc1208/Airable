package kr.co.airbridge.airable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_user_email)
    EditText userEmail;
    @Bind (R.id.login_user_password)
    EditText userPassword;

    LoginButton facebookLoginBtn;
    CallbackManager callbackManager; //facebook 로그인 callback

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();

        facebookLoginBtn = (LoginButton)findViewById(R.id.login_facebook_btn);
        facebookLoginBtn.setReadPermissions("user_friends");
        facebookLoginBtn.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {

                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onEmailLoginClick(View view) {

    }

}
