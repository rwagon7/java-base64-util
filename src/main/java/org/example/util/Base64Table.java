package org.example.util;

import java.util.*;

public class Base64Table {

    private final Map<String,String> table = new HashMap<String,String>();

    public Map<String, String> getTable() {
        return table;
    }

    private Integer counter = 0;

    public Base64Table() {
        this.upperCaseAlphabets();
        this.lowerCaseAlphabets();
        this.integers();
        this.symbols();
        this.padding();
        System.out.println(this.table.size());
    }

    private void upperCaseAlphabets(){
        counter = 0;
        for(Integer i=65; i<=90; i++) {
            Character base64Ch = (char) i.intValue();
            table.put(counter.toString(),base64Ch.toString());
            counter++;
        }
        //table.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void lowerCaseAlphabets(){

        for(Integer i=97; i<=122; i++) {
                Character base64Ch = (char) i.intValue();
            table.put(counter.toString(),base64Ch.toString());
                counter++;
        }
//        table.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void integers(){
        for(Integer i=0; i<=9; i++) {
            table.put(counter.toString(),i.toString());
            counter++;
        }
        //table2.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void symbols(){
        counter++;
        table.put(counter.toString(),"+");
        counter++;
        table.put(counter.toString(),"/");
    }

    private void padding(){
        counter++;
        table.put(counter.toString()," ");
    }




}
