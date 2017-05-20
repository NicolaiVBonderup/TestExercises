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
public class MongoSphereLocation {

    CoordinateSet loc;
    String name;
    String UID;

    public MongoSphereLocation(CoordinateSet loc, String name, String UID) {
        this.loc = loc;
        this.name = name;
        this.UID = UID;
    }

    public MongoSphereLocation() {
    }

    public CoordinateSet getLoc() {
        return loc;
    }

    public void setLoc(CoordinateSet loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

}
