package kr.co.airbridge.airable.model;

/**
 * Created by dajung han on 2016-01-11.
 */
public class Process {
    private int no;
    private String name;
    private int time;
    private String placeName;
    private String description;
    private int state;
    private int vertexid;

    public static final int INCLUDE_PROCESS = 0;
    public static final int EXCLUDE_PROCESS = -1;
    public static final int PASSED_PROCESS = 1;

    public Process (int no, String name, int time, String placeName, String description, int state, int vertexid) {
        this.no = no;
        this.name = name;
        this.time = time;
        this.placeName = placeName;
        this.description = description;
        this.state = state;
        this.vertexid = vertexid;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getDescription() {
        return description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getVertexid() {
        return vertexid;
    }
}
