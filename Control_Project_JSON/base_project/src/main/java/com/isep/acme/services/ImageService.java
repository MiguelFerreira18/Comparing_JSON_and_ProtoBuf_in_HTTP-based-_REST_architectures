package com.isep.acme.services;

import com.isep.acme.Dto.ImageDTO;
import com.isep.acme.model.H2Entity.ProdImage;
import com.isep.acme.repositories.h2Repos.Repos.ImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ImageService {

    private Resource image;
    private ProdImage id;
    private FileStorageService service;

    private ImageRepository repository;
    private String filename;


    public Iterable<ImageDTO> getImageProduct() {
        Iterable<ProdImage> p = repository.findAll();
        List<ImageDTO> iDto = new ArrayList();
        for (ProdImage pd : p) {
            iDto.add(pd.toDto());
        }

        return iDto;
    }


    public <ProdImage> Resource addImage(Resource image) {

        this.image = service.loadFileAsResource(filename);
        return image;
    }


}
