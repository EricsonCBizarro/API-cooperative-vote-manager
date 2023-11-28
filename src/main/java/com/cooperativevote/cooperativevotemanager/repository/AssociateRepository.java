package com.cooperativevote.cooperativevotemanager.repository;

import com.cooperativevote.cooperativevotemanager.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Boolean existsByDocument(String document);
}
