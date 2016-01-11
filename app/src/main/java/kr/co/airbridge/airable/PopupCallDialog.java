package kr.co.airbridge.airable;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.airbridge.airable.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PopupCallDialog extends Dialog{
    View.OnClickListener yesListener;
    View.OnClickListener cancelListener;
    String tel;

    @Bind(R.id.popup_button_call_yes)
    Button btnYes;
    @Bind(R.id.popup_button_call_cancel)
    Button btnCancel;
    @Bind(R.id.popup_textview_call_number)
    TextView textTel;

    public PopupCallDialog(Context context, View.OnClickListener yesListener, View.OnClickListener cancelListener, String tel){
        super(context);
        super.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.yesListener = yesListener;
        this.cancelListener = cancelListener;
        this.tel = tel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_call);
        ButterKnife.bind(this);

        if(yesListener != null && cancelListener != null){
            btnYes.setOnClickListener(yesListener);
            btnCancel.setOnClickListener(cancelListener);
            textTel.setText(tel);
        }
    }
}
