package kr.co.airbridge.airable;

public class Shop {
    private int no;
    private String title;
    private String info;
    private String location;
    private int floor;
    private String time;
    private String tel;
    private String image;
    private int mark;

    public Shop (int no, String title, String info, String location, int floor, String time, String tel, String image, int mark){
        this.no = no;
        this.title = title;
        this.info = info;
        this.location = location;
        this.floor = floor;
        this.time = time;
        this.tel = tel;
        this.image = image;
        this.mark = mark;
    }

    public int getNo(){
        return no;
    }

    public void setNo(){
        this.no = no;
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

    public int getFloor(){
        return floor;
    }

    public void setFloor(int floor){
        this.floor = floor;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
