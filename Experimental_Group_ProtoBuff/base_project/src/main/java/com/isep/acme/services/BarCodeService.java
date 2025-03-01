package com.isep.acme.services;


import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.repositories.h2Repos.Repos.ProductRepository;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;


public class BarCodeService {

    private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

     @Autowired
    private static ProductRepository pRepo;

    public static BufferedImage getBarcode(String sku) throws Exception {
        Optional<Product> product = pRepo.findBySku(sku);
        if (product.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not fOUND");
        }
        Barcode barcode = BarcodeFactory.createCode128(sku);
        barcode.setFont(BARCODE_TEXT_FONT);
        barcode.setLabel(sku);
        return BarcodeImageHandler.getImage(barcode);


    }
}
