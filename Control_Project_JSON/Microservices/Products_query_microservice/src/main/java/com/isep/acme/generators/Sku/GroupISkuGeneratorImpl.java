package com.isep.acme.generators.Sku;

import java.util.Random;

public class GroupISkuGeneratorImpl implements ISkuGenerator {
    @Override
    public String generateSku(String designation) {
        String sku = "";
        StringBuilder parte = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            parte.append(random.nextInt(10)); // 1 algarismo
            parte.append((char) (random.nextInt(26) + 'a')); // 1 letra minúscula
        }

        sku = parte.toString();
        parte = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            parte.append((char) (random.nextInt(26) + 'a')); // 1 letra minúscula
            parte.append(random.nextInt(10)); // 1 algarismo
        }

        sku = sku + parte.toString();

        String caracteresEspeciais = "!@#$%^&*()_+-=[]{};:,.<>?";
        char caractereEspecial = caracteresEspeciais.charAt(random.nextInt(caracteresEspeciais.length()));

        sku = sku + caractereEspecial;

        return sku;
    }
}
