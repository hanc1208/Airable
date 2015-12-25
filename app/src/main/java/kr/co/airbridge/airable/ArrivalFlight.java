package kr.co.airbridge.airable;

/**
 * Created by pso99_000 on 2015-12-12.
 */
public class ArrivalFlight {
    // 모든 변수명은 '인천국제공항공사 OpenAPI'의 변수명과 동일합니다.
    private String airline;               //항공사 - e.g.대한항공
    private String flightId;              //항공 편명 - e.g. KE854
    private String scheduleDataTime;    // 도착예정시간 - e.g. 0020
    private String estimatedDataTime;   // 도착변경시간 - e.g. 0046
    private String airport;               // 출발공함 - e.g. 베이징
    private String gatenumber;           // 탑승구 번호 - e.g. 17
    private String carousel;             // 수하물 수취대 번호 - e.g. 5
    private String exitnumber;          // 출구 번호 - e.g. B
    private String remark;              // 운항상태 (도착, 결항, 지연, 회항, 착륙) - e.g. 도착

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

    public String getGatenumber() {
        return gatenumber;
    }

    public void setGatenumber(String gatenumber) {
        this.gatenumber = gatenumber;
    }

    public String getCarousel() {
        return carousel;
    }

    public void setCarousel(String carousel) {
        this.carousel = carousel;
    }

    public String getExitnumber() {
        return exitnumber;
    }

    public void setExitnumber(String exitnumber) {
        this.exitnumber = exitnumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
