package kr.co.airbridge.airable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class DepartureFlightResponse {
    @Element
    private Header header;
    @Element
    private Body body;

    public Header getHeader(){return header;}
    public Body getBody(){return body;}
}

@Root(name ="header")
class Header{
    @Element(name = "resultCode")
    private int resultCode;
    @Element(name="resultMsg")
    private String resultMsg;

    public int getResultCode(){return resultCode;}
    public String getResultMsg(){return resultMsg;}
}

@Root(name ="body")
class Body{
    @ElementList(type=DepartureFlight.class, required=false)
    List<DepartureFlight> items;
}