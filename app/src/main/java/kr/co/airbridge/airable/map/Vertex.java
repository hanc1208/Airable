package kr.co.airbridge.airable.map;

public class Vertex {
    private int id;
    private int x;
    private int y;
    private int[] adjacencies;
    private java.util.Map<String, Integer> rssiMap;

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getAdjacencies() {
        return adjacencies;
    }

    public java.util.Map<String, Integer> getRssiMap() {
        return rssiMap;
    }
}
