/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Private
 */
public class Book {

    private long UID;
    private String title;
    private List<Author> author;
    //private List<Location> locations;
    private List<MongoSphereLocation> locations;
    private String text;

    public Book(long UID, String title, List<Author> author, List<MongoSphereLocation> locations, String text) {
        this.UID = UID;
        this.title = title;
        this.author = author;
        this.locations = locations;
        this.text = text;
    }

    public Book(String title, List<Author> author, List<MongoSphereLocation> locations, String text) {
        this.title = title;
        this.author = author;
        this.locations = locations;
        this.text = text;
    }

    public Book() {
        this.author = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public long getUID() {
        return UID;
    }

    public void setUID(long UID) {
        this.UID = UID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
    
    public void addAuthor(Author author) {
        this.author.add(author);
    }

    public List<MongoSphereLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<MongoSphereLocation> locations) {
        this.locations = locations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
