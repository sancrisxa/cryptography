package br.com.sancrisxa.cryptography.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {
    private static final StrongTextEncryptor encryptor;

    static {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(System.getenv("APP_KEY"));
    }

    public static String encrypt(String rawText) {
        return encryptor.encrypt(rawText);
    }

    public static String descrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }
}
