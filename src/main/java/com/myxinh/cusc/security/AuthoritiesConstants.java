package com.myxinh.cusc.security;

public class AuthoritiesConstants {
    private static final String TOKEN_SECRET_KEY = "myxinh";


    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static String getTokenSecretKey() {
        return TOKEN_SECRET_KEY;
    }
}
