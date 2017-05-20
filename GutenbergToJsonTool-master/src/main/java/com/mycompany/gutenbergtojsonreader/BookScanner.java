/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergtojsonreader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Private
 */
public class BookScanner {

    public BookScanner() {

    }

    public List<File> getAllGutenbergBooks(final File folder) {

        List<File> bookFiles = new ArrayList<>();

        //int count = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getAllGutenbergBooks(fileEntry);
            } else {
                bookFiles.add(fileEntry);
            }
        }

        return bookFiles;
    }

}
