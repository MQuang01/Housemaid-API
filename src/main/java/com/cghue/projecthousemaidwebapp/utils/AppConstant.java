package com.cghue.projecthousemaidwebapp.utils;

import lombok.Getter;

public class AppConstant {
    public static final String CODE = "CG%sHD";
    public static final String SIGNATURE =
            """
                    --------------------\s
                    House maid system service CO.\s
                    Telephone: 0123456789\s
                    Email: housemaid.huecity@gmail.com\s
                    Website: http://localhost:3000/\s
                    """;

    public static final String URL_CONFIRM_ORDER = "http://localhost:3000/confirm?cod=$%s$&userId=%s";

    public static String get() {
        return CODE;
    }
    public static String getSignature() {
        return SIGNATURE;
    }

    public static String getUrlConfirmOrder() {
        return URL_CONFIRM_ORDER;
    }

}
