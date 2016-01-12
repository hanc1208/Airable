package kr.co.airbridge.airable.model;

import kr.co.airbridge.airable.map.Map;

public class Floor {
    private int id;
    private int number;
    private Map map;
    private Store[] stores;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Map getMap() {
        return map;
    }

    public Store[] getStores() {
        return stores;
    }
}
