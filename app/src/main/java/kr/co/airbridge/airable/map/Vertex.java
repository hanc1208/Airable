package kr.co.airbridge.airable.map;

public class Vertex {
    private int id;
    private int x;
    private int y;
    private int[] adjacencies;

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
}
