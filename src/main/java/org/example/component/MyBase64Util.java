package org.example.component;

import org.example.Interface.IMyBase64Util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

public class MyBase64Util implements IMyBase64Util{

    public String encoder(String input, Map<String,String> table){

        //input
        StringBuilder sbuilder = new StringBuilder();;

        //process
        byte[] byteArray = input.getBytes();

        StringBuilder intbuilder = new StringBuilder();
        for(int i=0; i< byteArray.length; i++){
            intbuilder.delete(0,intbuilder.length());
            int bit = byteArray[i];
            while(bit > 0){
                intbuilder.append(bit % 2);
                bit = bit / 2;
            }

            for(int j=0; j<(8-intbuilder.length()); j++) {
                intbuilder.append("0");
            }
            sbuilder.append(intbuilder.reverse());
            }

        //output
       // uncomment to check output  System.out.println(sbuilder.toString());

        //input
        StringBuilder binaryArray = new StringBuilder();
        binaryArray.append(sbuilder.toString());
        int length = binaryArray.length();
        StringBuilder base64 = new StringBuilder();

        for(int j =0; j < length ; j+=6){
            if(length-j < 6) {
                base64.append("=");
                break;
            }
            String sextantString = binaryArray.substring(j,j+6);
            int base64CharIndex = 0;
            for(int i=5; i>=0; i--){
               base64CharIndex += (Integer.valueOf(String.valueOf(sextantString.charAt(i))))*Math.pow(2,5-i);
            }
            base64.append(table.get(String.valueOf(base64CharIndex)));

        }

        return base64.toString();
    };

    public String decoder(String input,Map<String,String> table) throws UnsupportedEncodingException {
        //BASE64 TO SEXTENT
        //input
        StringBuilder base64builder = new StringBuilder();
        base64builder.append(input);
        int length = base64builder.length();
        List<Integer> sextantArray = new ArrayList<Integer>();
        //STEP 1 INPUT
        Integer counter = 0;

        //process
        while (counter < length) {

            Iterator<Map.Entry<String, String>> itr = table.entrySet().iterator();
            String scounter = String.valueOf(base64builder.charAt(0));
            base64builder.deleteCharAt(0);
            while (itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                if (entry.getValue().equals(scounter)) {
                    sextantArray.add(Integer.parseInt(entry.getKey().toString()));
                    break;
                }
            }
            counter++;

        }
        //uncomment to view sextant array
        // System.out.println(Arrays.toString(sextantArray.toArray()));

        
        //SEXTENT TO OCTENT
        ArrayList<String> base8_array = new ArrayList<String>();
        StringBuilder base2Builder = new StringBuilder();

        for(int i=0; i<sextantArray.size(); i++){

            int base6_element = sextantArray.get(i).intValue();

            while(base6_element > 0){

                base2Builder.append(base6_element % 2);
                base6_element = base6_element / 2;

            }

            base8_array.add(base2Builder.toString());
            base2Builder.setLength(0);
        }

        System.out.println(Arrays.toString(base8_array.toArray()));

        //BINARY TO DECIMAL
        StringBuilder base10Builder = new StringBuilder();
        ArrayList<String> base10_array = new ArrayList<String>();

        for(String s : base8_array){
            base10Builder.append(s);
        }

        for(int i =0; i<base10Builder.length(); i+=9){
            if(base10Builder.length() - i < 9) break;
            String element = base10Builder.substring(i,i+9);
            base10_array.add(element);
        }
            base10Builder.setLength(0);
        for(int i=0; i<base10_array.size(); i++){
            int base10_record = Integer.parseInt(base10_array.get(i).toString(),10);
            Character character = Character.forDigit(base10_record, 10);
            base10Builder.append(character);
        }

        return base10Builder.toString();

        }
}