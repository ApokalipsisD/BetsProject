package com.bets.betsproject.service.api;

import com.bets.betsproject.model.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(Team team);

    List<Team> getAllTeams();

    Team getTeamById(Integer id);

    Team updateTeam(Team team, Integer id);

    void deleteTeam(Integer id);
}
