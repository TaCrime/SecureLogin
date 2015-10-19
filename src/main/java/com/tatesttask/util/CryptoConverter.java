package com.tatesttask.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.Key;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "MySuperSecretKey".getBytes();

    @Override
    public String convertToDatabaseColumn(String ccNumber) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encode(cipher.doFinal(ccNumber.getBytes()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.decode(dbData)));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
