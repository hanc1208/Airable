package kr.co.airbridge.airable.model;

public class TravelOption {
    private int id;
    private String name;
    private boolean check;

    public TravelOption () {

    }

    public TravelOption(int toId, String toName) {
        this.id = id;
        this.name = name;
        this.check = false;
    }

    public void setToId(int toId) {
        this.id = toId;
    }
    public int getToId() {
        return this.id;
    }
    public void setToName(String toName) {
        this.name = name;
    }
    public String getToName() {
        return this.name;
    }
    public void setToCheck(boolean check) {
        this.check = check;
    }
    public boolean getToCheck() {
        return this.check;
    }
}
