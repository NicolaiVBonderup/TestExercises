/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergtojsonreader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Author;
import entity.Book;
import entity.City;
import entity.CoordinateSet;
import entity.Location;
import entity.MongoSphereLocation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Private
 */
public class SingleBookScannerThread implements Runnable {

    private File bookFile;
    private List<City> cities;
    BufferedReader br = null;
    FileReader fr = null;
    private Thread t;
    private int counter = 0;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    FileWriter fw = null;
    BufferedWriter bw = null;
    private static final Object lock = new Object();
    //List<Location> cityList;
    List<MongoSphereLocation> cityList;
    Map<String, City> cityNames;
    Set<String> foundCities;

    public SingleBookScannerThread(File bookFile, List<City> cities) throws IOException {
        this.bookFile = bookFile;
        this.cities = cities;
        cityList = new ArrayList<>();
        cityNames = new HashMap<>();
        cities.stream().forEach((city) -> {
            cityNames.put(city.getAsciiName().toLowerCase(), city);
        });
    }

    @Override
    public void run() {
        try {
            scanBook(bookFile);
        } catch (IOException ex) {

        }
    }

    public void start() {
        System.out.println("Starting thread");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    private void scanBook(File book) throws IOException {
        foundCities = new HashSet<>();

        fr = new FileReader(book);
        br = new BufferedReader(fr);

        Book newBook = new Book();

        String sCurrentLine = "";
        String fullText = "";

        while ((sCurrentLine = br.readLine()) != null) {

            fullText = fullText + "\n" + sCurrentLine;

            if (sCurrentLine.contains("Title: ")) {

                newBook.setTitle(sCurrentLine.substring(7, sCurrentLine.length()));

            } else if (sCurrentLine.contains("Author: ")) {

                String authorString = sCurrentLine.substring(8, sCurrentLine.length());

                newBook.addAuthor(new Author(authorString));

                while (true) {
                    String nextLine = br.readLine();
                    if (nextLine.equals("")) {
                        break;
                    }
                    nextLine = nextLine.trim();
                    newBook.getAuthor().add(new Author(nextLine));

                }

            } else if (sCurrentLine.toLowerCase().contains("ebook #")) {
                // TODO: Inelegant hack, figure out something better later. Probably. Maybe. Probably not.
                String[] split = sCurrentLine.split("#");
                String[] trimmed = split[1].split("]");
                try {
                    newBook.setUID(Long.parseLong(trimmed[0]));
                } catch (NumberFormatException ex) {

                }
            } else {
                scanWordForCity(sCurrentLine);
            }
        }

        fr.close();
        br.close();

        if (newBook.getAuthor() != null && newBook.getTitle() != null) {
            newBook.setText(book.getName());
            if (newBook.getUID() == 0) {
                newBook.setUID(Long.parseLong(book.getName().replace(".txt", "")));
            }

            newBook.setLocations(cityList);

            saveBookToJson(newBook);

        }

    }

    public void scanWordForCity(String inputStr) {
        String[] words = inputStr.replace("\n", "").split(" ");
        String twoWordString;
        int word1 = 0;
        int word2 = 1;

        for (String word : words) {
            if (words.length != 1) {
                twoWordString = words[word1] + " " + words[word2];
                if (!foundCities.contains(word.toLowerCase())) {
                    if (cityNames.containsKey(word.toLowerCase())) {
                        foundCities.add(word.toLowerCase());
                        City city = cityNames.get(word.toLowerCase());
                        float[] latLong = new float[2];
                        latLong[1] = Float.parseFloat(city.getLatitude());
                        latLong[0] = Float.parseFloat(city.getLongitude());
                        //cityList.add(new Location(Long.parseLong(city.getGeoNameId()), Float.parseFloat(city.getLatitude()), Float.parseFloat(city.getLongitude()), city.getName()));
                        cityList.add(new MongoSphereLocation(new CoordinateSet("Point",latLong),city.getName(),city.getGeoNameId()));
                    } else if (cityNames.containsKey(twoWordString.toLowerCase())) {
                        foundCities.add(twoWordString.toLowerCase());
                        City city = cityNames.get(twoWordString.toLowerCase());
                        float[] latLong = new float[2];
                        latLong[1] = Float.parseFloat(city.getLatitude());
                        latLong[0] = Float.parseFloat(city.getLongitude());
                        //cityList.add(new Location(Long.parseLong(city.getGeoNameId()), Float.parseFloat(city.getLatitude()), Float.parseFloat(city.getLongitude()), city.getName()));
                        cityList.add(new MongoSphereLocation(new CoordinateSet("Point",latLong),city.getName(),city.getGeoNameId()));
                    }
                }
                word1++;
                if (word1 != words.length - 1) {
                    word2++;
                }
            }
        }
    }

    public List<Location> stringContainsCityFromList(String inputStr, List<City> items, Map<String, City> cityNames) {
        List<Location> cityList = new ArrayList<>();

        String[] words = inputStr.split("[!-~]* ");
        Set<String> uniqueWords = new HashSet<>();

        uniqueWords.addAll(Arrays.asList(words));
        City city;

        String uniqueSplit;
        for (String uniqueWord : uniqueWords) {
            uniqueSplit = uniqueWord.replace("\n", "");
            if (cityNames.containsKey(uniqueSplit.toLowerCase())) {
                city = cityNames.get(uniqueSplit.toLowerCase());
                cityList.add(new Location(Long.parseLong(city.getGeoNameId()), Float.parseFloat(city.getLatitude()), Float.parseFloat(city.getLongitude()), city.getName()));
            }
        }

        return cityList;

    }

    public void saveBookToJson(Book book) throws IOException {

        String newJsonString = gson.toJson(book) + ",";
        fw = new FileWriter("booksJson.txt", true);
        bw = new BufferedWriter(fw);
        try (PrintWriter out = new PrintWriter(bw)) {
            out.println(newJsonString);
            StaticBooks.count();
            BookScanner bs = new BookScanner();
            out.close();

        }

        bw.close();
        fw.close();

    }

}
