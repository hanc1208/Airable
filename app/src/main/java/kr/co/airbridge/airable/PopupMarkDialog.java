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


public class PopupMarkDialog extends Dialog{
    View.OnClickListener yesListener;
    View.OnClickListener cancelListener;

    @Bind(R.id.popup_button_yes)
    Button btnYes;
    @Bind(R.id.popup_button_cancel)
    Button btnCancel;

    public PopupMarkDialog(Context context, View.OnClickListener yesListener, View.OnClickListener cancelListener){
        super(context);
        super.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.yesListener = yesListener;
        this.cancelListener = cancelListener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_mark);
        ButterKnife.bind(this);

        if(yesListener != null && cancelListener != null){
            btnYes.setOnClickListener(yesListener);
            btnCancel.setOnClickListener(cancelListener);
        }
    }
}
