package com.example.costume_rental.repository;

import com.example.costume_rental.model.Costume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumeRepository extends JpaRepository<Costume, Integer> {
    Costume findCostumeById(Integer id);
}
