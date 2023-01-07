package com.cmc.selfdevelopment.global.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest extends JasyptConfig {

    @Test
    public void jasypt_encrypt_decrypt_test() {
        String plainText = "cmc1234!";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("likelion");

        String encryptedText = jasypt.encrypt(plainText);
        String decryptedText = jasypt.decrypt("encryptedText");
        System.out.println(decryptedText);
        System.out.println("here" + encryptedText);

        assertThat(plainText).isEqualTo(decryptedText);
    }
}