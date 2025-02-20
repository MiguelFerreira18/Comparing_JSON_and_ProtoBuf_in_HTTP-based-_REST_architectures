package com.isep.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isep.acme.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
    
}
