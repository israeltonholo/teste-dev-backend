package com.olisaude.israeltestedevbackend.api.controller;

import com.olisaude.israeltestedevbackend.domain.model.Client;
import com.olisaude.israeltestedevbackend.domain.model.HealthIssues;
import com.olisaude.israeltestedevbackend.domain.service.ClientService;
import com.olisaude.israeltestedevbackend.domain.service.HealthIssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/problemas-de-saude")
public class HealthIssuesController {

    @Autowired
    private final HealthIssuesService healthIssuesService;

    public HealthIssuesController(HealthIssuesService healthIssuesService) {
        this.healthIssuesService = healthIssuesService;
    }

    @GetMapping("/todas")
    public List<HealthIssues> listAllHealthIssues() {
        return healthIssuesService.getAllIssues();
    }

    @GetMapping("/todas/{userId}")
    @ResponseBody
    public List<HealthIssues> listAllHealthIssuesByUserId(@PathVariable Long userId) {
        return healthIssuesService.getAllIssuesByUserId(userId);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<HealthIssues> saveHealthIssues(@RequestBody String newIssue) {
        return healthIssuesService.setIssue(newIssue);
    }

    @GetMapping("/{id}")
    public HealthIssues getHealthIssues(@PathVariable Long id) {
        return healthIssuesService.getIssueById(id);
    }

    @DeleteMapping("/{id}")
    public HealthIssues deleteClient(@PathVariable Long id) {
        return healthIssuesService.deleteIssueById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<HealthIssues> putHealthIssues(@RequestBody String editedHealthIssues, @PathVariable Long id) {
        return healthIssuesService.putIssueById(id, editedHealthIssues);
    }

}
