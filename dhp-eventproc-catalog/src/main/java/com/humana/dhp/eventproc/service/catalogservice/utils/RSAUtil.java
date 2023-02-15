package com.humana.dhp.eventproc.service.catalogservice.utils;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSAUtil {

    public static String encryptData(PublicKey publicKey, String data) {
        try {
            if (publicKey == null) {
                return null;
            }
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] inputArray = data.getBytes("UTF-8");
            int inputLength = inputArray.length;
            int MAX_ENCRYPT_BLOCK = 245;
            int offSet = 0;
            byte[] resultBytes = {};
            byte[] cache = {};
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                    offSet += MAX_ENCRYPT_BLOCK;
                } else {
                    cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                    offSet = inputLength;
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
            }
            return Base64.encodeBase64String(resultBytes);
        } catch (Exception ex) {
//TODO
        }
        return "";
    }

    public static String decryptData(PrivateKey privateKey, String data) {
        try {
            if (privateKey == null) {
                return null;
            }
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decoded = Base64.decodeBase64(data);
            byte[] cache = new byte[2048];
            byte[] resultBytes = {};
            int inputLength = decoded.length;
            int MAX_ENCRYPT_BLOCK = 256;
            int offSet = 0;
            if (inputLength > 256) {
                while (inputLength - offSet > 0) {
                    if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                        cache = cipher.doFinal(Base64.decodeBase64(data.getBytes()), offSet, MAX_ENCRYPT_BLOCK);
                        offSet += MAX_ENCRYPT_BLOCK;
                    } else {
                        cache = cipher.doFinal(Base64.decodeBase64(data.getBytes()), offSet, inputLength - offSet);
                        offSet = inputLength;
                    }
                    resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                    System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
                }
            } else {
                //cipherData = cipher.doFinal(resultBytes);
                resultBytes = cipher.doFinal(decoded);
            }
            Charset UTF8_CHARSET = Charset.forName("UTF-8");
            String re = new String(resultBytes, UTF8_CHARSET);
            return re;
        } catch (Exception ex) {
//TODO
        }
        return "";
    }

    public static RSAPublicKey createPublicKey(String key) {
        byte[] keyBytes = Base64.decodeBase64(key);

        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            //   logger.info("RSAUtils" + RSAUtils.class.getName() + ":" + ex);
        }
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException ex) {
            //TODO
        }
        return null;
    }

    public static RSAPrivateKey createPrivateKey(String key) {
        byte[] keyBytes = Base64.decodeBase64(key);

        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            // logger.info("RSAUtils" + RSAUtils.class.getName() + ":" + ex);
        }
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException ex) {
//TODO
        }
        return null;
    }
}
