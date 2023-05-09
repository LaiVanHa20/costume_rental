package com.example.costume_rental.repository;

import com.example.costume_rental.model.CostumeReturnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumeReturnDetailRepository extends JpaRepository<CostumeReturnDetail, Integer> {
}
