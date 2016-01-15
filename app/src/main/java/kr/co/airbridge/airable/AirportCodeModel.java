package kr.co.airbridge.airable;


import org.simpleframework.xml.Element;

public class AirportCodeModel {


    private String airport;               // 도착지공함 - e.g. 두바이
    private String flightId;              //항공 편명 - e.g. KE951Y

    public AirportCodeModel(String mairport, String mflightId){

        this.airport=mairport;
        this.flightId=mflightId;

    };
    public AirportCodeModel(){}

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

}

