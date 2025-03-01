package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.ProdImage;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("h2Profile")
@Repository
public interface ImageRepository extends CrudRepository<ProdImage, Long> {
}
