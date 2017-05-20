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
public class Author {
    
    private long UID;
    private String name;

    public Author(long UID, String name) {
        this.UID = UID;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public long getUID() {
        return UID;
    }

    public void setUID(long UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
