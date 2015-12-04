package kr.co.airbridge.airable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class RegisterFormActivity extends AppCompatActivity {

    @Bind(R.id.register_form_user_image)
    ImageView userImage;
    @Bind(R.id.register_form_user_name)
    EditText userName;
    @Bind(R.id.register_form_user_email)
    EditText userEmail;
    @Bind(R.id.register_form_user_password)
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        ButterKnife.bind(this);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();
    }

    public void onRegisterClick(View view) {

    }

    @OnClick(R.id.register_form_agreement)
    public void onAgreementClick() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
