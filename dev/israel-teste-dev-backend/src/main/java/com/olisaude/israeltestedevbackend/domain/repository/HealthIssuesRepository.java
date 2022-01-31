package com.olisaude.israeltestedevbackend.domain.repository;

import com.olisaude.israeltestedevbackend.domain.model.HealthIssues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthIssuesRepository extends JpaRepository<HealthIssues, Long> {
    List<HealthIssues> findByUserId(Long UserId);
}
