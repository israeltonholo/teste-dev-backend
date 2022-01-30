package com.olisaude.israeltestedevbackend.domain.model;

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
    private String levelDisease;

    @Column(name = "fk_client_user_id")
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

    public String getLevelDisease() {
        return levelDisease;
    }

    public void setLevelDisease(String levelDisease) {
        this.levelDisease = levelDisease;
    }
}
