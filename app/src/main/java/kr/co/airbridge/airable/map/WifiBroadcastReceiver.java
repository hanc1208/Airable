package kr.co.airbridge.airable.map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WifiBroadcastReceiver extends BroadcastReceiver {
    private Context context;

    private WifiManager wifiManager;
    private WifiBroadcastListener wifiBroadcastListener;

    private boolean scanning = false;

    private List<Wifi> wifiList = new ArrayList<>();

    public WifiBroadcastReceiver(Context context, WifiBroadcastListener wifiBroadcastListener) {
        this.context = context;

        this.wifiBroadcastListener = wifiBroadcastListener;

        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.getConnectionInfo();

        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

    }

    public void startScan() {
        IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        context.registerReceiver(this, intentFilter);
        wifiManager.startScan();
        scanning = true;
    }

    public void stopScan() {
        scanning = false;
        context.unregisterReceiver(this);
    }

    public boolean isScanning() {
        return scanning;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            wifiList.clear();

            List<ScanResult> scanResults = wifiManager.getScanResults();
            for (int i = 0; i < scanResults.size(); i++) {
                ScanResult scanResult = scanResults.get(i);
                Wifi wifi = new Wifi();
                wifi.ssid = scanResult.SSID.replace("insider-1", "airable");
                wifi.rssi = scanResult.level + 100;
                wifiList.add(wifi);
            }

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    wifiBroadcastListener.onReceive(wifiList);
                }
            });
            wifiManager.startScan();
        } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            if (!wifiManager.isWifiEnabled()) {
                Toast.makeText(context, "Wifi 연결이 끊겼습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface WifiBroadcastListener {
        void onReceive(List<Wifi> wifiList);
    }
}
