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
import java.util.Arrays;
import java.util.List;


public class Tarskereso_services implements Tarskereso_interface{

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
            String like = new String();
            like = "";

            for (int i = 0; i < user.likeok.size(); i++) {
                like += user.likeok.get(i).toString() + ",";
            }
            data[5] = like.substring(0,like.length()-1);
            writer.writeNext(data);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    };
    public List<User> fromFile(String path){
        List<User> felhasznalok = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while((nextRecord = csvReader.readNext()) != null){
                User felhasznalo = new User();
                felhasznalo.setId(Integer.parseInt(nextRecord[0]));
                int j = 1;
                switch(nextRecord[1]){
                    case("ferfi"):
                        felhasznalo.setNem(Nem_enum.ferfi);
                        break;
                    case("no"):
                        felhasznalo.setNem(Nem_enum.no);
                        break;
                }
                felhasznalo.setNev(nextRecord[2]);
                felhasznalo.setKor(Integer.parseInt(nextRecord[3]));
                felhasznalo.setLeiras(nextRecord[4]);
                List<String> temp_list = Arrays.asList(nextRecord[5].split(","));
                ArrayList<Integer> l = new ArrayList<>();

                for(int i = 0; i < temp_list.size(); i++){
                    l.add(Integer.parseInt(temp_list.get(i)));
                }
               felhasznalo.likeok = l;
                felhasznalok.add(felhasznalo);

            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return felhasznalok;
    }
}
