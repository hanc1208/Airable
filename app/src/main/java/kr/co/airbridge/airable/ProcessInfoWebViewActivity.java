package kr.co.airbridge.airable;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class ProcessInfoWebViewActivity extends Activity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_info_webview);

        Intent getnumberintent = getIntent();

        int getNum = (int) getnumberintent.getIntExtra("processNum", -1);

        webview = (WebView) findViewById(R.id.webView);
        webview.setWebViewClient(new WebClient());
        WebSettings set = webview.getSettings();
        webview.setInitialScale(1);
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        set.setLoadWithOverviewMode(true);
        set.setUseWideViewPort(true);




        switch (getNum) {


            case 1:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/8/index.jsp");
                break;
            case 2:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/4/2/4/2/index.jsp");
                break;
            case 3:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/4/2/3/index.jsp");
                break;
            case 4:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/7/index.jsp");
                break;
            case 5:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/6/index.jsp");
                break;
            case 6:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/6/index.jsp");
                break;
            case 7:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/6/index.jsp");
                break;
            case 8:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/2/index.jsp");
                break;
            case 9:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/3/index.jsp");
                break;
            case 10:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/6/index.jsp");
                break;
            case 11:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/3/index.jsp");
                break;
            case 12:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/6/index.jsp");
                break;
            case 13:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/6/index.jsp");
                break;
            case 14:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/5/index.jsp");
                break;
            case 15:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/4/index.jsp");
                break;
            case 16:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/5/index.jsp");
                break;
            case 17:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/4/index.jsp");
                break;
            case 18:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/8/index.jsp");
                break;
            case 19:
                webview.loadUrl("http://www.airport.kr/pa/ko/d/2/2/8/index.jsp");
                break;
        }


        ImageView ImageView = (ImageView)findViewById(R.id.webview_close);

        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

}
class WebClient extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}