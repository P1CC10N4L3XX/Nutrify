package com.dicii.ispw.project.applicationcontroller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;


public class OAuthGoogle {

    private OAuthGoogle(){}
    //funzione per la creazione di una stringa casuale di lunghezza length
    private static String random64Url(int length){
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[length];
        secureRandom.nextBytes(codeVerifier);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
    }

    private static String generateChallenge(String codeVerifier) throws NoSuchAlgorithmException{
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }


    public static String generateQuery() throws NoSuchAlgorithmException, IOException{
        Properties cfg = new Properties();
        try(FileInputStream f = new FileInputStream(Objects.requireNonNull(OAuthGoogle.class.getResource("/config/Cfg.properties")).toString().replace("file:", ""))) {
            cfg.load(f);
        }catch(FileNotFoundException e) {
            e.getStackTrace();
        }
        String state = random64Url(32);
        String codeVerifier = random64Url(32);
        String codeChallenge = generateChallenge(codeVerifier);
        return String.format("%s?response_type=code&scope=%s&redirect_uri=%s&client_id=%s&state=%s&code_challenge=%s&code_challenge_method=%s",
                cfg.getProperty("authorizationEndPoint"),
                cfg.getProperty("scope"),
                cfg.getProperty("redirectUri"),
                cfg.getProperty("clientID"),
                state,
                codeChallenge,
                cfg.getProperty("codeChallengeMethod")
        );
    }
}
