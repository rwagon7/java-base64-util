package org.example.Interface;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface IMyBase64Util {

    public String encoder(String input, Map<String, String> table);

    public String decoder(char[] input,int length) throws UnsupportedEncodingException;
}
