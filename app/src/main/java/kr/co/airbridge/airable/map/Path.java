package kr.co.airbridge.airable.map;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;

import java.util.*;
import java.util.Map;

public class Path {
    private List<Vertex> vertexes;
    private SparseArray<Drawable> markerMap = new SparseArray<>();
    private int lineColor;
    private int lineWidth;

    public Path(List<Vertex> vertexes, int lineColor, int lineWidth) {
        this.vertexes = vertexes;
        this.lineColor = lineColor;
        this.lineWidth = lineWidth;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public void putMarker(int vertexId, Drawable drawable) {
        markerMap.put(vertexId, drawable);
    }

    public void removeMarker(int vertexId) {
        markerMap.remove(vertexId);
    }

    public SparseArray<Drawable> getMarkerMap() {
        return markerMap;
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }
}
