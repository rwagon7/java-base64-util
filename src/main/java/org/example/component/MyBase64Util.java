package org.example.component;

import org.example.Interface.IMyBase64Util;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

public class MyBase64Util implements IMyBase64Util{

    public String encoder(String input, Map<String,String> table){

        //input
        StringBuilder sbuilder = new StringBuilder();;

        //process
        byte[] byteArray = input.getBytes();
        System.out.println(Arrays.toString(byteArray));

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
        System.out.println(sbuilder.toString());

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
            System.out.println(sextantString);
            int base64CharIndex = 0;
            for(int i=5; i>=0; i--){
               base64CharIndex += (Integer.valueOf(String.valueOf(sextantString.charAt(i))))*Math.pow(2,5-i);
            }
            System.out.println(base64CharIndex);
            base64.append(table.get(String.valueOf(base64CharIndex)));

        }

        return base64.toString();
    };

    public String decoder(String input){
        return null;
    };
}