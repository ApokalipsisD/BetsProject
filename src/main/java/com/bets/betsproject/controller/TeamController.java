package com.bets.betsproject.controller;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Team;
import com.bets.betsproject.service.api.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
        if (Objects.nonNull(teamService.getByTeamName(team.getName()))) {
            throw new ResourceNotFoundException("Team is already exists");
        }
        return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
}
