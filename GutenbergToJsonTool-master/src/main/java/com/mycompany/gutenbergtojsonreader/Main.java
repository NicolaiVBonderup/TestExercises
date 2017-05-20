/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergtojsonreader;

import entity.City;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author Private
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        //CityScanner cs = new CityScanner();
        //cs.createMongoSphereList();
        generateBooks();
        //generateSQL();
    }

    public static void generateSQL() throws FileNotFoundException, IOException {

        SQLGenerator sg = new SQLGenerator();

        //sg.generateSQLScriptFromBooks();
        sg.generateCitySQLScript();

    }

    public static void generateBooks() throws FileNotFoundException, IOException {
        BookScanner bs = new BookScanner();
        CityScanner cs = new CityScanner();

        
        final File folder = new File("C:/gutenbooks/");
        List<City> cities = cs.getCitiesFromJson("citiesJson.txt");
        List<File> books = bs.getAllGutenbergBooks(folder);
        
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        
        for (File file : books) {
            Runnable worker = new SingleBookScannerThread(file, cities);
            executorService.execute(worker);
            while (true) {
                if (executorService.getActiveCount() < 10) {
                    break;
                }
            }
        }

        executorService.shutdown();

    }

}
