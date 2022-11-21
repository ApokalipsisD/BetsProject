package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Team;
import com.bets.betsproject.repository.TeamRepository;
import com.bets.betsproject.service.api.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Integer id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Team", "Id", id)
        );
    }

    @Override
    public Team updateTeam(Team team, Integer id) {
        Team newTeam = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team", "Id", id));
        newTeam.setName(team.getName());
        teamRepository.save(newTeam);
        return newTeam;
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team", "Id", id));
        teamRepository.deleteById(id);
    }
}
