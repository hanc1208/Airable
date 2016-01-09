package kr.co.airbridge.airable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class HttpTestActivity extends AppCompatActivity {
    ///////////////////////////////////////////////////////////////
    RetrofitServer retrofitServer = new RetrofitServer();
    ///////////////////////////////////////////////////////////////

    @Bind(R.id.httptest_login_email)
    EditText loginEmail;
    @Bind(R.id.httptest_login_password)
    EditText loginPassword;
    @Bind(R.id.httptest_login_result_textview)
    TextView loginResult;

    @Bind(R.id.httptest_register_name_edittext)
    EditText registerName;
    @Bind(R.id.httptest_register_password)
    EditText registerPassword;
    @Bind(R.id.httptest_register_email)
    EditText registerEmail;
    @Bind(R.id.httptest_register_result_textview)
    TextView registerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.httptest_login_submit_button)
    public void onLoginClicked(){
        String email = loginEmail.getText().toString();
        String pw = loginPassword.getText().toString();

        retrofitServer.postLogin(email, pw).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                loginResult.setText(response.body().toString());
                Log.i("mytag", "Login Response");
            }

            @Override
            public void onFailure(Throwable t) {
                loginResult.setText(t.getMessage());
                Log.i("mytag", "Login Failure");
            }
        });
    }


    @OnClick(R.id.httptest_register_submit_button)
    public void onRegisterClicked(){
        String name = registerName.getText().toString();
        String email = registerEmail.getText().toString();
        String pw = registerPassword.getText().toString();

        retrofitServer.postRegister(name, email, pw).enqueue(new Callback<String>(){
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit){
                registerResult.setText(response.body().toString());
                Log.i("mytag", "Register Response");
            }
            @Override
            public void onFailure(Throwable t){
                registerResult.setText(t.getMessage());
                Log.i("mytag", "Register Failure");
            }
        });
    }
}
