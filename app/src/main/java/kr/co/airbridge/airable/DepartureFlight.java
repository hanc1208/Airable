package kr.co.airbridge.airable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class DepartureFlight {
    // 모든 변수명은 '인천국제공항공사 OpenAPI'의 변수명과 동일합니다.
    @Element(name = "airline", required=false)
    private String airline;               //항공사 - e.g.대한항공
    @Element(name = "airport", required=false)
    private String airport;               // 도착지공함 - e.g. 두바이
    @Element(name = "airportcode", required=false)
    private String airportCode;               // 도착지공함 - e.g. 두바이
    @Element(name = "flightId", required=false)
    private String flightId;              //항공 편명 - e.g. KE951Y
    @Element(name = "scheduleDateTime", required=false)
    private String scheduleDateTime;    // 도착예정시간 - e.g. 0005
    @Element(name = "estimatedDateTime", required=false)
    private String estimatedDateTime;   // 도착변경시간 - e.g. 0002
    @Element(name = "chkinrange", required=false)
    private String chkinrange;           // 체크인 카운터 - e.g. H25-H36
    @Element(name = "gatenumber", required=false)
    private String gatenumber;           // 탑승구 번호 - e.g. 122
    @Element(name = "remark", required=false)
    private String remark;                // 운항상태 (출발, 결항, 지연, 탑승중, 마감예정, 탑승마감, 탑승준비) - e.g. 출발

    public DepartureFlight(String mTime, String mTimeChange, String mCity, String mAirNum, String mremark, String mchkinrage, String mgatenumber){
        this.scheduleDateTime=mTime;
        this.estimatedDateTime=mTimeChange;
        this.airport=mCity;
        this.flightId=mAirNum;
        this.chkinrange=mchkinrage;
        this.gatenumber=mgatenumber;
        this.remark=mremark;
    }
    public DepartureFlight(){}


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getScheduleDataTime() {
        return scheduleDateTime;
    }

    public void setScheduleDataTime(String scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public String getEstimatedDataTime() {
        return estimatedDateTime;
    }

    public void setEstimatedDataTime(String estimatedDateTime) {
        this.estimatedDateTime = estimatedDateTime;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getChkinrange() {
        return chkinrange;
    }

    public void setChkinrange(String chkinrange) {
        this.chkinrange = chkinrange;
    }

    public String getGatenumber() {
        return gatenumber;
    }

    public void setGatenumber(String gatenumber) {
        this.gatenumber = gatenumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAirportCode() { return airportCode; }

    public void setAirportCode(String airportcode) { this.airportCode = airportcode; }
}