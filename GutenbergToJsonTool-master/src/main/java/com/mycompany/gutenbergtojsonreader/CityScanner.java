/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergtojsonreader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.City;
import entity.CoordinateSet;
import entity.MongoSphereLocation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Private
 */
public class CityScanner {

    BufferedReader br = null;
    FileReader fr = null;

    public CityScanner() throws FileNotFoundException {
        //fr = new FileReader("C:/cities15000.txt");
        //fr = new FileReader("C:/cities15000.txt");
        //br = new BufferedReader(fr);
    }

    public List<City> getFullCityList() throws IOException {

        List<City> cityList = new ArrayList<>();

        String sCurrentLine;

        while ((sCurrentLine = br.readLine()) != null) {
            String[] line = sCurrentLine.split("\t");
            String[] alternateNames = line[3].split(",");
            cityList.add(new City(line[0], line[1], line[2], alternateNames, line[4], line[5], line[14]));
        }

        return cityList;
    }

    public void saveCitiesToJson(List<City> cityList) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String newJsonString = gson.toJson(cityList);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("citiesJson.txt"))) {
            bw.write(newJsonString);
        } catch (IOException ex) {
            Logger.getLogger(BookScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createMongoSphereList() throws IOException {

        BufferedReader bre;
        List<City> cities;
        try (FileReader fre = new FileReader("citiesJson.txt")) {
            bre = new BufferedReader(fre);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            cities = gson.fromJson(bre, new TypeToken<List<City>>() {
            }.getType());
        }
        bre.close();

        List<MongoSphereLocation> spheres = new ArrayList<>();
        MongoSphereLocation msl;
        float[] latLong;
        for (City city : cities) {
            latLong = new float[2];
            latLong[1] = Float.parseFloat(city.getLatitude());
            latLong[0] = Float.parseFloat(city.getLongitude());
            msl = new MongoSphereLocation(new CoordinateSet("Point", latLong), city.getAsciiName(), city.getGeoNameId());
            spheres.add(msl);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String newJsonString = gson.toJson(spheres);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("spheresJson.json"))) {
            bw.write(newJsonString);
        } catch (IOException ex) {
            Logger.getLogger(BookScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<City> getCitiesFromJson(String cityFile) throws FileNotFoundException, IOException {
        BufferedReader bre;
        List<City> cities;
        try (FileReader fre = new FileReader("citiesJson.txt")) {
            bre = new BufferedReader(fre);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            cities = gson.fromJson(bre, new TypeToken<List<City>>() {
            }.getType());
        }
        bre.close();

        return cities;

    }

}
