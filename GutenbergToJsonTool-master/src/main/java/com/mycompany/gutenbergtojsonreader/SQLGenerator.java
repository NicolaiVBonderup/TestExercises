/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergtojsonreader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Author;
import entity.Book;
import entity.City;
import entity.Location;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Private
 */
public class SQLGenerator {

    BufferedReader br = null;
    FileReader fr = null;
    List<Long> insertedUIDs = new ArrayList<>();
    List<Location> usedLocations = new ArrayList<>();

    public SQLGenerator() throws FileNotFoundException {
        fr = new FileReader("booksJson.txt");
        br = new BufferedReader(fr);
    }

    public void generateCitySQLScript() throws IOException {

        List<City> cities = new ArrayList<>();

        try {
            CityScanner cs = new CityScanner();
            cities = cs.getCitiesFromJson("citiesJson.txt");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SQLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sqlStr = "";
        String insertStartStr = "INSERT INTO location VALUES ";
        String insertStr;

        
        for (City city : cities) {

            //insertStr = "INSERT INTO location VALUES (#,¤,%,&);";
            insertStr = "(#,¤,%,&),";
            String replace = insertStr
                    .replace("#", city.getGeoNameId())
                    .replace("¤", city.getLatitude())
                    .replace("%", city.getLongitude())
                    .replace("&", "\"" + city.getAsciiName() + "\"");

            insertStartStr += replace;
                    //+ "\n";

        }
        
        sqlStr = insertStartStr.substring(0,insertStartStr.length()-1) + ";";

        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("locationSql.txt"))) {
                bw.write(sqlStr);
            }
        } catch (IOException ex) {
            Logger.getLogger(BookScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/*
    public void generateSQLScriptFromBooks() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Book> books = gson.fromJson(br, new TypeToken<List<Book>>() {
        }.getType());
        HashMap<String, Long> authors = new HashMap<>();

        long UID = 0;

        // Gathers a key-value pair of authors and generates a UID for them.
        // Allows for adding UID's to the authors on every book.
        for (Book book : books) {
            if (book.getAuthor() != null) {
                for (Author auth : book.getAuthor()) {
                    if (authors.get(auth.getName()) == null) {
                        authors.put(auth.getName(), UID);
                        UID++;
                    }
                }
            }
        }

        // Adds UIDs for every Author object.
        for (Book book : books) {
            if (book.getAuthor() != null) {
                for (Author auth : book.getAuthor()) {
                    auth.setUID(authors.get(auth.getName()));
                }
            }
        }

       
        String sqlStr = "INSERT INTO book VALUES ";
        String authSqlStr = "INSERT INTO author VALUES ";
        String junctionBookSqlStr = "INSERT INTO author_book VALUES ";
        String insertStr, authorStr, bookStr, authJuncStr, insertSqlStr;
        String junctionLocSqlStr = "";
        String insertAuthStr = "";
        String insertBookJuncStr = "";
        String insertBookStr = "";

        for (Book book : books) {

            if (book.getAuthor() != null) {

                for (Author auth : book.getAuthor()) {
                    if (!insertedUIDs.contains(auth.getUID())) {
                        authorStr = "(#,¤),";
                        String replace = authorStr
                                .replace("#", "" + auth.getUID())
                                .replace("¤", "\"" + auth.getName()
                                        .replace(")", "")
                                        .replace("(", "")
                                        .replace(";", "")
                                        .replace("\"", "") + "\"");

                        insertAuthStr += replace;

                        insertedUIDs.add(auth.getUID());

                        authJuncStr = "(#,¤),";
                        replace = authJuncStr
                                .replace("#", "" + auth.getUID())
                                .replace("¤", "" + book.getUID());

                        insertBookJuncStr += replace;

                    }
                }
            }

            if (book.getUID() != 0 && book.getTitle() != null && book.getText() != null) {

                bookStr = "(#,¤,%),";

                String replace = bookStr
                        .replace("#", "" + book.getUID())
                        .replace("¤", "\"" + book.getTitle()
                                .replace(")", "")
                                .replace("(", "")
                                .replace(";", "")
                                .replace("\"", "") + "\"")
                        .replace("%", "\"" + book.getText() + "\"");

                insertBookStr += replace;
            }

            insertSqlStr = "INSERT INTO book_location VALUES ";
            for (Location loc : book.getLocations()) {
                if (book.getUID() != 0 && book.getTitle() != null && book.getText() != null) {
                    insertStr = "(#,¤),";
                    String replace = insertStr
                            .replace("#", "" + book.getUID())
                            .replace("¤", "" + loc.getUID());

                    insertSqlStr += replace;

                }
            }
            if (!insertSqlStr.equals("INSERT INTO book_location VALUES ")) {

                junctionLocSqlStr += insertSqlStr.substring(0, insertSqlStr.length() - 1) + ";\n";
            }
        }

        if (!insertAuthStr.equals("")) {
            authSqlStr += insertAuthStr.substring(0, insertAuthStr.length() - 1) + ";\n";
        }
        if (!insertBookJuncStr.equals("")) {
            junctionBookSqlStr += insertBookJuncStr.substring(0, insertBookJuncStr.length() - 1) + ";\n";
        }
        if (!insertBookStr.equals("")) {
            sqlStr += insertBookStr.substring(0, insertBookStr.length() - 1) + ";\n";
        }

        authSqlStr += "\n" + sqlStr + "\n" + junctionBookSqlStr + "\n" + junctionLocSqlStr;

        String fullSqlStr = "SET FOREIGN_KEY_CHECKS = 0;\nSTART TRANSACTION; \n" + authSqlStr + "\n COMMIT;";

        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("bookAuthorSql.txt"))) {
                bw.write(fullSqlStr);
            }
        } catch (IOException ex) {
            Logger.getLogger(BookScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
