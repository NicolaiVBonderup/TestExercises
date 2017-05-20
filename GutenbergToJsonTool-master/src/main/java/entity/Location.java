/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Private
 */
public class Location {
    
    private long UID;
    private Float latitude;
    private Float longitude;
    private String name;

    public Location(long UID, Float latitude, Float longitude, String name) {
        this.UID = UID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public Location() {
    }

    public long getUID() {
        return UID;
    }

    public void setUID(long UID) {
        this.UID = UID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
