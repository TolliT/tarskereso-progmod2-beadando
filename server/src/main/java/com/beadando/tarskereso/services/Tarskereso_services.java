package com.beadando.tarskereso.services;

import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tarskereso_services implements Tarskereso_interface{

    /**
     * Function handling the logic of writing data to temporary databases in the form of CSV files
     * @param path String - Path to file location
     * @param user User - Specified user's data to write
     */
    public void toFile(String path, User user){
        File file = new File(path);
        try {
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] data = new String[6];
            data[0] = user.getId().toString();
            data[1] = user.getNem().toString();
            data[2] = user.getNev();
            data[3] = user.getKor().toString();
            data[4] = user.getLeiras();
            data[5] = user.getKedvel().toString();

            writer.writeNext(data);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }


    /**
     * Function handling the logic of reading data from temporary databases in the form of CSV files
     * @param path String - Path to file location
     */
    public ArrayList<User> fromFile(String path){
        ArrayList<User> felhasznalok = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while((nextRecord = csvReader.readNext()) != null){
                User felhasznalo = new User();
                felhasznalo.setId(Integer.parseInt(nextRecord[0]));
                switch (nextRecord[1]) {
                    case ("ferfi") -> felhasznalo.setNem(Nem_enum.ferfi);
                    case ("no") -> felhasznalo.setNem(Nem_enum.no);
                }
                felhasznalo.setNev(nextRecord[2]);
                felhasznalo.setKor(Integer.parseInt(nextRecord[3]));
                felhasznalo.setLeiras(nextRecord[4]);
                if(nextRecord[5].equals("true")){
                    felhasznalo.setKedvel(Boolean.TRUE);
                }
                else{
                    felhasznalo.setKedvel(Boolean.FALSE);
                }
                felhasznalok.add(felhasznalo);
            }
            csvReader.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return felhasznalok;
    }


    /**
     * Function handling the logic of filtering the list of users, based on the preferences received from the
     * frontend website
     * @param genderPref String - Preferred gender specified by the user
     * @param minAge Integer - Preferred minimum age by the user
     * @param maxAge Integer - Preferred maximum age by the user
     */
    public ArrayList<User> userFilter(String genderPref, Integer minAge, Integer maxAge){
        ArrayList<User> users = fromFile("server/src/main/resources/Tarskereso_db.csv");
        return getUsers(genderPref, minAge, maxAge, users);
    }


    /**
     * Function handling the logic of getting the users with specified data, from the database
     * @param genderPref String - Preferred gender specified by the user
     * @param minAge Integer - Preferred minimum age by the user
     * @param maxAge Integer - Preferred maximum age by the user
     * @param users ArrayList<User> - list of users to be returned
     */
    private ArrayList<User> getUsers(String genderPref, Integer minAge, Integer maxAge, ArrayList<User> users) {
        ArrayList<User> filtered = new ArrayList<>();

        for (User user : users) {
            if (user.getNem().toString().equals(genderPref) && user.getKor() >= minAge && user.getKor() <= maxAge) {
                filtered.add(user);
            }
        }
        return filtered;
    }


    /**
     * Function handling the testing of the userFilter() function
     * @param users ArrayList<User> - list of users to be returned
     * @param genderPref String - Preferred gender specified by the user
     * @param minAge Integer - Preferred minimum age by the user
     * @param maxAge Integer - Preferred maximum age by the use
     */
    public ArrayList<User> userFilterTest(ArrayList<User> users, String genderPref, Integer minAge, Integer maxAge){
        return getUsers(genderPref, minAge, maxAge, users);
    }


    /**
     * Function handling the deletion of all the contents of the databasae
     * @param path String - Path to file location
     */
    public void flushDB(String path){
        File file = new File(path);
        try {
            FileWriter outputfile = new FileWriter(file, false);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.flush();
            writer.close();
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }

}
