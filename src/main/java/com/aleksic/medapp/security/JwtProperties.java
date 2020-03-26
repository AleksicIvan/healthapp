package com.aleksic.medapp.security;

public class JwtProperties {
    public static final String SECRET = "redroom666";
    public static final int EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
