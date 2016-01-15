package kr.co.airbridge.airable;

public class AirListDepartingFlight {
    // 모든 변수명은 '인천국제공항공사 OpenAPI'의 변수명과 동일합니다.
    private String airline;               //항공사 - e.g.대한항공
    private String flightId;              //항공 편명 - e.g. KE951Y
    private String scheduleDataTime;    // 도착예정시간 - e.g. 0005
    private String estimatedDataTime;   // 도착변경시간 - e.g. 0002
    private String airport;               // 도착지공함 - e.g. 두바이
    private String chkinrange;           // 체크인 카운터 - e.g. H25-H36
    private String gatenumber;           // 탑승구 번호 - e.g. 122
    private String remark;                // 운항상태 (출발, 결항, 지연, 탑승중, 마감예정, 탑승마감, 탑승준비) - e.g. 출발
    private String airport_code;

    private String rawdate;




    public AirListDepartingFlight(String mTime, String mTimeChange, String mCity, String mAirNum, String mAirPort,String mchkinrange,String mgatenumber,String mairport_code,String rawtime){
        this.scheduleDataTime=mTime;
        this.estimatedDataTime=mTimeChange;
        this.airport=mCity;
        this.flightId=mAirNum;
        this.airline=mAirPort;
        this.chkinrange=mchkinrange;
        this.gatenumber=mgatenumber;
        this.airport_code=mairport_code;
        this.rawdate =rawtime;
    }
    public AirListDepartingFlight(){}


    public String getRawdate() {
        return rawdate;
    }

    public void setRawdate(String rawdate) {
        this.rawdate = rawdate;
    }


    public String getAirport_code() {
        return airport_code;
    }

    public void setAirport_code(String airport_code) {
        this.airport_code = airport_code;
    }

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
        return scheduleDataTime;
    }

    public void setScheduleDataTime(String scheduleDataTime) {
        this.scheduleDataTime = scheduleDataTime;
    }

    public String getEstimatedDataTime() {
        return estimatedDataTime;
    }

    public void setEstimatedDataTime(String estimatedDataTime) {
        this.estimatedDataTime = estimatedDataTime;
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
}
