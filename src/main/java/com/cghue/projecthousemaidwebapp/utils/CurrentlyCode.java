package com.cghue.projecthousemaidwebapp.utils;

public class CurrentlyCode {
    public static final String CODE = "CG %s HD";

    public static String get(String code) {
        return String.format(CODE, code);
    }
}
