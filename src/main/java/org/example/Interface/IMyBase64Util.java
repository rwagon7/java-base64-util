package org.example.Interface;

import java.util.Map;

public interface IMyBase64Util {

    public String encoder(String input, Map<String,String> table);

    public String decoder(String input);
}
