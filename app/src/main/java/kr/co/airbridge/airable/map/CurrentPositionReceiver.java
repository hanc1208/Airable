package kr.co.airbridge.airable.map;

import android.content.Context;
import android.graphics.Point;

import java.util.*;

public class CurrentPositionReceiver implements WifiBroadcastReceiver.WifiBroadcastListener {
    private Map map;
    private Set<String> ssidSet = new HashSet<>();

    private WifiBroadcastReceiver wifiBroadcastReceiver;
    private CurrentPositionListener currentPositionListener;

    public CurrentPositionReceiver(Context context, Map map, CurrentPositionListener currentPositionListener) {
        this.wifiBroadcastReceiver = new WifiBroadcastReceiver(context, this);
        this.map = map;
        this.currentPositionListener = currentPositionListener;

        for (Vertex vertex : map.getVertexes()) {
            for (String ssid : vertex.getRssiMap().keySet()) {
                ssidSet.add(ssid);
            }
        }
    }

    @Override
    public void onReceive(List<Wifi> wifiList) {
        List<Wifi> newWifiList = new ArrayList<>(wifiList);
        for (Wifi wifi : wifiList) {
            if (!ssidSet.contains(wifi.ssid)) {
                newWifiList.remove(wifi);
            }
        }

        wifiList = newWifiList;

        Vertex expectedVertex = null;
        int minDifference = Integer.MAX_VALUE;

        for (Vertex vertex : map.getVertexes()) {
            int difference = 0;
            boolean allFound = true;

            for (java.util.Map.Entry<String, Integer> entry: vertex.getRssiMap().entrySet()) {
                boolean found = false;

                for (Wifi wifi : wifiList) {
                    if (wifi.ssid.equals(entry.getKey())) {
                        found = true;
                        difference += Math.pow(wifi.rssi - entry.getValue(), 2);
                    }
                }

                if (!found) {
                    allFound = false;
                    break;
                }
            }

            if (allFound && difference < minDifference) {
                expectedVertex = vertex;
                minDifference = difference;
            }
        }

        int random = new Random().nextInt(10);
        if (random <= 6) {
            expectedVertex = map.getVertexes()[0];
        } else if (random <= 8) {
            expectedVertex = map.getVertexes()[1];
        } else {
            expectedVertex = map.getVertexes()[2];
        }

        if (expectedVertex != null) {
            currentPositionListener.onReceive(new Point(expectedVertex.getX(), expectedVertex.getY()));
        }
    }

    public void startScan() {
        wifiBroadcastReceiver.startScan();
    }

    public void stopScan() {
        wifiBroadcastReceiver.stopScan();
    }

    public boolean isScanning() {
        return wifiBroadcastReceiver.isScanning();
    }

    public interface CurrentPositionListener {
        void onReceive(Point currentPosition);
    }
}
