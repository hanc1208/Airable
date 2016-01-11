package kr.co.airbridge.airable;

public class Shop {
    private String entrpskoreannm; // 매장명
    private String trtmntprdlstkoreannm; // 취급품목
    private String lckoreannm; // 위치
    private String servicetime; // 영업시간
    private String tel; // 전화번호

    public Shop (String name, String items, String location, String time, String tel){
        entrpskoreannm = name;
        trtmntprdlstkoreannm = items;
        lckoreannm = location;
        servicetime = time;
        this.tel = tel;
    }

    public String getEntrpskoreannm() {
        return entrpskoreannm;
    }

    public void setEntrpskoreannm(String entrpskoreannm) {
        this.entrpskoreannm = entrpskoreannm;
    }

    public String getTrtmntprdlstkoreannm() {
        return trtmntprdlstkoreannm;
    }

    public void setTrtmntprdlstkoreannm(String trtmntprdlstkoreannm) {
        this.trtmntprdlstkoreannm = trtmntprdlstkoreannm;
    }

    public String getLckoreannm() {
        return lckoreannm;
    }

    public void setLckoreannm(String lckoreannm) {
        this.lckoreannm = lckoreannm;
    }

    public String getServicetime() {
        return servicetime;
    }

    public void setServicetime(String servicetime) {
        this.servicetime = servicetime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
