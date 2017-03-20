/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wikiadmin.clientnotification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Считываем данные из файла
 * @author aleksdem
 */
public class FileToArray {

    ArrayList<String> newList = new ArrayList<>();

    public FileToArray() throws FileNotFoundException, IOException {
        try {
            File textFile = new File ("clients.txt");
            FileReader filereader = new FileReader(textFile);
                    BufferedReader br = new BufferedReader(filereader);

            String currentLine = "start";
            while( currentLine !=null){
                currentLine = br.readLine();
                newList.add(currentLine);}

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileToArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}