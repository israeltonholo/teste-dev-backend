package com.olisaude.israeltestedevbackend.domain.repository;

import com.olisaude.israeltestedevbackend.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
