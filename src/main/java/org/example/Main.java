package org.example;

//import org.example.component.MapOfBase64Alphabets;
import org.example.component.MyBase64Util;
import org.example.util.Base64Table;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Started application | Intializing lookup table");
//        MapOfBase64Alphabets table = new MapOfBase64Alphabets();
        Base64Table table = new Base64Table();
        System.out.println("Successfully loaded the lookup table");
        MyBase64Util base64 = new MyBase64Util();
        System.out.println(base64.encoder("Manyhandsmakelightwork",table.getTable()));
        //TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu
        System.out.println(base64.decoder("TWFueWhhbmRzbWFrZWxpZ2h0d29ya=",table.getTable()));;
        //

    }
}