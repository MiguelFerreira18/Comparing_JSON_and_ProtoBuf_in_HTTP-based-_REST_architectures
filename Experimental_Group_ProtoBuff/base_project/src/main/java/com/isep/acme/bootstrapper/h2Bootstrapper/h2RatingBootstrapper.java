package com.isep.acme.bootstrapper.h2Bootstrapper;

import com.isep.acme.model.H2Entity.Rating;
import com.isep.acme.repositories.h2Repos.Repos.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Profile("bootstrap")
@Profile("h2Profile")//h2Profile
@Component
public class h2RatingBootstrapper implements CommandLineRunner {


    @Autowired
    private RatingRepository repository;

    @Override
    public void run(String... args) throws Exception {

        if (repository.findByRate(0.5).isEmpty()) {
            Rating rate05 = new Rating(0.5);
            repository.save(rate05);
        }

        if (repository.findByRate(1.0).isEmpty()) {
            Rating rate1 = new Rating(1.0);
            repository.save(rate1);
        }

        if (repository.findByRate(1.5).isEmpty()) {
            Rating rate15 = new Rating(1.5);
            repository.save(rate15);
        }

        if (repository.findByRate(2.0).isEmpty()) {
            Rating rate2 = new Rating(2.0);
            repository.save(rate2);
        }

        if (repository.findByRate(2.5).isEmpty()) {
            Rating rate25 = new Rating(2.5);
            repository.save(rate25);
        }

        if (repository.findByRate(3.0).isEmpty()) {
            Rating rate3 = new Rating(3.0);
            repository.save(rate3);
        }

        if (repository.findByRate(3.5).isEmpty()) {
            Rating rate35 = new Rating(3.5);
            repository.save(rate35);
        }



        if (repository.findByRate(4.5).isEmpty()) {
            Rating rate45 = new Rating(4.5);
            repository.save(rate45);
        }

        if (repository.findByRate(5.0).isEmpty()) {
            Rating rate5 = new Rating(5.0);
            repository.save(rate5);
        }
    }
}
