package kr.co.airbridge.airable.model;

/**
 * Created by dajung han on 2016-01-02.
 */
public class RecycleItem {
    String title;
    String content;
    String place;
    int time;
    int state;
    int processNum;
    int vertexid;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPlace() {
        return place;
    }

    public int getTime() {
        return time;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setProcessNum(int processNum) {
        this.processNum = processNum;
    }

    public int getProcessNum() {
        return processNum;
    }

    public void setVerexid(int vertexid) {
        this.vertexid = vertexid;
    }

    public int getVertexid() {
        return vertexid;
    }
}
