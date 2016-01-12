package kr.co.airbridge.airable;

public class Shop {
    private String title;
    private String info;
    private String location;
    private String time;
    private String tel;

    public Shop (String title, String info, String location, String time, String tel){
        this.title = title;
        this.info = info;
        this.location = location;
        this.time = time;
        this.tel = tel;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
