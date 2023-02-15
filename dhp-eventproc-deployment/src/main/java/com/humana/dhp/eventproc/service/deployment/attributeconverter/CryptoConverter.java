package com.humana.dhp.eventproc.service.deployment.attributeconverter;

import com.humana.dhp.eventproc.service.deployment.exception.FlowDeploymentException;
import com.humana.dhp.eventproc.service.deployment.utils.CryptoUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Converter
@Slf4j
@NoArgsConstructor
public class CryptoConverter implements AttributeConverter<byte[], String> {

  private String keyvaultSecret = "keyvaultSecret";

  private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

  /**
   * Must be one of {128 120 112 104 96}
   */
  private static final int TAG_LENGTH_BIT = 128;
  private static final int IV_LENGTH_BYTE = 12;
  private static final int SALT_LENGTH_BYTE = 16;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  /**
   * Return a base64 encoded AES encrypted text
   *
   * @param plainText plainText
   * @param password  password
   * @return String
   */
  public static String encrypt(byte[] plainText, String password) throws
      NoSuchAlgorithmException,
      InvalidKeySpecException,
      NoSuchPaddingException,
      IllegalBlockSizeException,
      BadPaddingException,
      InvalidAlgorithmParameterException,
      InvalidKeyException {
    byte[] salt = CryptoUtils.getRandomNonce(SALT_LENGTH_BYTE);
    byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);
    SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);
    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
    cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    byte[] cipherText = cipher.doFinal(plainText);
    byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
        .put(iv)
        .put(salt)
        .put(cipherText)
        .array();
    return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
  }

  /**
   * We need the same password, salt and iv to decrypt it
   *
   * @param encryptedValue encryptedValue
   * @param password       password
   * @return String
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   * @throws InvalidAlgorithmParameterException
   * @throws InvalidKeyException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws NoSuchPaddingException
   */
  public static String decrypt(String encryptedValue, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
    byte[] decode = Base64.getDecoder().decode(encryptedValue.getBytes(UTF_8));
    ByteBuffer bb = ByteBuffer.wrap(decode);
    byte[] iv = new byte[IV_LENGTH_BYTE];
    bb.get(iv);
    byte[] salt = new byte[SALT_LENGTH_BYTE];
    bb.get(salt);
    byte[] cipherText = new byte[bb.remaining()];
    bb.get(cipherText);
    SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);
    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
    cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    byte[] plainText = cipher.doFinal(cipherText);
    return new String(plainText, UTF_8);
  }

  @Override
  public String convertToDatabaseColumn(byte[] attribute) {
    try {
      if (attribute == null) {
        return null;
      }
      return encrypt(attribute, keyvaultSecret);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new FlowDeploymentException(e);
    }
  }

  @Override
  public byte[] convertToEntityAttribute(String dbData) {
    try {
      if (dbData == null) {
        return new byte[0];
      }
      return decrypt(dbData, keyvaultSecret).getBytes(UTF_8);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new FlowDeploymentException(e);
    }
  }
}
