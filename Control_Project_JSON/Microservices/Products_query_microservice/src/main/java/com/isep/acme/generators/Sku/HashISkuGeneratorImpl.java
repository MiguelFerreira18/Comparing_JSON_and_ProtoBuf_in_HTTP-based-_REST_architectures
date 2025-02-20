package com.isep.acme.generators.Sku;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashISkuGeneratorImpl implements ISkuGenerator {

    private String calculateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            long numericHashCode = 0;
            for (byte hashByte : hashBytes) {
                numericHashCode = (numericHashCode << 8) | (hashByte & 0xff);
            }
            return numericHashCode + "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String generateSku(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            throw new IllegalArgumentException("Designation cannot be null or empty");
        }
        String sku = calculateHash(designation);
        String hexaString = Long.toHexString(Long.parseLong(sku));
        System.out.println("SKU: " + hexaString);
        String tenDigits = "";
        if (hexaString.length() % 2 == 0) {
            tenDigits = hexaString.substring((hexaString.length()) / 2 - 5, (hexaString.length()) / 2 + 5);
        } else {
            tenDigits = hexaString.substring((hexaString.length() / 2) - 5, (hexaString.length()) / 2 + 6);
        }
        System.out.println("Ten digits: " + tenDigits);

        return tenDigits;
    }
}
