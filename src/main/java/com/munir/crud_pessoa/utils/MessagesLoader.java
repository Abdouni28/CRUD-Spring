package com.munir.crud_pessoa.utils;
  
import java.util.ResourceBundle;

public final class MessagesLoader {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");

    private MessagesLoader() {}

    public static String loadMessage(String key) {

        return BUNDLE.getString(key);
    }
}