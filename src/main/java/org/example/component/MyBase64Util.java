package org.example.component;

import org.example.Interface.IMyBase64Util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

public class MyBase64Util implements IMyBase64Util{

    static final int SIZE = 100;


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

    public String decoder(char[] encoded,int len_str) {

        char []decoded_String;

        decoded_String = new char[SIZE];

        int i, j, k = 0;

        // stores the bitstream.
        int num = 0;

        // count_bits stores current
        // number of bits in num.
        int count_bits = 0;

        // selects 4 characters from
        // encoded String at a time.
        // find the position of each encoded
        // character in char_set and stores in num.
        for (i = 0; i < len_str; i += 4)
        {
            num = 0; count_bits = 0;
            for (j = 0; j < 4; j++)
            {

                // make space for 6 bits.
                if (encoded[i + j] != '=')
                {
                    num = num << 6;
                    count_bits += 6;
                }

            /* Finding the position of each encoded
            character in char_set
            and storing in "num", use OR
            '|' operator to store bits.*/

                // encoded[i + j] = 'E', 'E' - 'A' = 5
                // 'E' has 5th position in char_set.
                if (encoded[i + j] >= 'A' && encoded[i + j] <= 'Z')
                    num = num | (encoded[i + j] - 'A');

                    // encoded[i + j] = 'e', 'e' - 'a' = 5,
                    // 5 + 26 = 31, 'e' has 31st position in char_set.
                else if (encoded[i + j] >= 'a' && encoded[i + j] <= 'z')
                    num = num | (encoded[i + j] - 'a' + 26);

                    // encoded[i + j] = '8', '8' - '0' = 8
                    // 8 + 52 = 60, '8' has 60th position in char_set.
                else if (encoded[i + j] >= '0' && encoded[i + j] <= '9')
                    num = num | (encoded[i + j] - '0' + 52);

                    // '+' occurs in 62nd position in char_set.
                else if (encoded[i + j] == '+')
                    num = num | 62;

                    // '/' occurs in 63rd position in char_set.
                else if (encoded[i + j] == '/')
                    num = num | 63;

                    // ( str[i + j] == '=' ) remove 2 bits
                    // to delete appended bits during encoding.
                else
                {
                    num = num >> 2;
                    count_bits -= 2;
                }
            }

            while (count_bits != 0) {
                count_bits -= 8;

                // 255 in binary is 11111111
                decoded_String[k++] = (char)
                        ((num >> count_bits) & 255);
            }

        }
        return Arrays.toString(decoded_String);
    }
}