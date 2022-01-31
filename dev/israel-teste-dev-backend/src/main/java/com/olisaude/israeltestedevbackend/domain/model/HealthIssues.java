package com.olisaude.israeltestedevbackend.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "health_issues_card")
public class HealthIssues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "level_disease")
    @JsonFormat(shape=JsonFormat.Shape.NUMBER)
    private Long levelDisease;

    @Column(name = "fk_client_user_id")
    @JsonFormat(shape=JsonFormat.Shape.NUMBER)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLevelDisease() {
        return levelDisease;
    }

    public void setLevelDisease(Long levelDisease) {
        this.levelDisease = levelDisease;
    }
}
