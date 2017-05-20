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
public class CoordinateSet {
    
    String type;
    float[] coordinates;

    public CoordinateSet(String type, float[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public CoordinateSet() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }
    
    
    
}
