package kr.co.airbridge.airable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class ShopDetailActivity extends AppCompatActivity {
    @Bind(R.id.shop_detail_layout)
    LinearLayout shopDetailLayout;
    @Bind(R.id.shop_detail_location)
    TextView shopLocation;
    @Bind(R.id.shop_detail_time)
    TextView shopTime;
    @Bind(R.id.shop_detail_info)
    TextView shopInfo;
    @Bind(R.id.shop_detail_image)
    ImageView shopImage;
    @Bind(R.id.mark_button)
    ImageButton markBtn;
    @Bind(R.id.call_button)
    ImageButton callBtn;
    @Bind(R.id.shop_detail_toolbar)
    Toolbar toolbar;

    PopupMarkDialog popupMarkDialog;
    PopupCallDialog popupCallDialog;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);
        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.shop_detail_toolbar);
        activityUtility.setNavigationAsBack();

        shopDetailLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        extras = getIntent().getExtras();
        if(extras == null){
            // Test code starts
            shopImage.setImageResource(R.drawable.user_image_default);
            shopLocation.setText("여객터미널(일반지역) 3층\nF 체크인 카운터 부근");
            shopTime.setText("05 : 00 ~ 22 : 00");
            shopInfo.setText("저희 매장은 소화제, 진통제 등 여행의 필수품인\n구급약을 두루 갖추고 있습니다.\n행복한 여행을 위한 준비, 저희 매장에서 하세요!");
            // Test code ends
        }else{
            int imageResource;
            switch(extras.getString("shopImage")){
                case "burger.png":
                    imageResource = R.drawable.burger;
                    break;
                case "ricecake.png":
                    imageResource = R.drawable.ricecake;
                    break;
                case "bag.png":
                    imageResource = R.drawable.bag;
                    break;
                case "taco.png":
                    imageResource = R.drawable.taco;
                    break;
                case "cosmetic.png":
                    imageResource = R.drawable.cosmetic;
                    break;
                default:
                    imageResource = R.drawable.user_image_default;
                    break;
            }
            shopImage.setImageResource(imageResource); // Default image 사용
            shopLocation.setText(extras.getString("shopLocation"));
            shopTime.setText(extras.getString("shopTime"));
            shopInfo.setText(extras.getString("shopInfo"));
            toolbar.setTitle(extras.getString("shopTitle"));
            setSupportActionBar(toolbar);
            activityUtility.setNavigationAsBack();
        }


        // ImageButton 클릭 시 이미지가 바뀌는 효과를 적용한다.
        markBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ((ImageButton) v).setImageResource(R.drawable.mark_2);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton) v).setImageResource(R.drawable.mark_1);
                    onMarkClicked(); // == onClickEvent
                }
                return true;
            }
        });

        callBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ((ImageButton) v).setImageResource(R.drawable.call_2);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton) v).setImageResource(R.drawable.call_1);
                    onCallClicked(); // == onClickEvent
                }
                return true;
            }
        });

    }

    // 찜하기 버튼 클릭 이벤트
    public void onMarkClicked(){
        popupMarkDialog =
                new PopupMarkDialog(this, popupMarkYesListener, popupMarkCancelListener);
        popupMarkDialog.show();
    }

    // 전화 걸기 버튼 클릭 이벤트
    public void onCallClicked(){
        popupCallDialog =
                new PopupCallDialog(this, popupCallYesListener, popupCallCancelListener, extras.getString("shopTel"));
        popupCallDialog.show();
    }

    // 찜하기 팝업 창에서 '확인' 선택 이벤트
    private View.OnClickListener popupMarkYesListener = new View.OnClickListener(){
        public void onClick(View v){
            // Do Something Here
            Toast.makeText(getApplicationContext(), "Mark", Toast.LENGTH_SHORT).show(); // Test
            popupMarkDialog.dismiss();
        }
    };

    // 찜하기 팝업 창에서 '취소' 선택 이벤트
    private View.OnClickListener popupMarkCancelListener = new View.OnClickListener(){
        public void onClick(View v){
            popupMarkDialog.dismiss();
        }
    };


    // 전화 걸기 팝업 창에서 '연결' 선택 이벤트
    private View.OnClickListener popupCallYesListener = new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+extras.getString("shopTel"))); // Test
            startActivity(intent);
            popupCallDialog.dismiss();
        }
    };

    // 전화 걸기 팝업 창에서 '취소' 선택 이벤트
    private View.OnClickListener popupCallCancelListener = new View.OnClickListener(){
        public void onClick(View v){
            popupCallDialog.dismiss();
        }
    };
}
