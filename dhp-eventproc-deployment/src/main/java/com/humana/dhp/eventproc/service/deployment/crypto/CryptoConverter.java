package com.humana.dhp.eventproc.service.deployment.crypto;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Converter
@Slf4j
public class CryptoConverter implements AttributeConverter<String, String> {
    // @Value("${always-encrypted-key}")
    private static String keyvaultSecret = "password_ne-day-la-password";
    private static SecretKeySpec secretKey;
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public static void setKey(final String myKey) {
        MessageDigest sha;
        byte[] key;
        try {
            sha = MessageDigest.getInstance("SHA-1");
            key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error(e.toString());
        }
    }

    @Override
    public String convertToDatabaseColumn(String originData) {
        setKey(keyvaultSecret);
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(c.doFinal(originData.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        setKey(keyvaultSecret);
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(c.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
