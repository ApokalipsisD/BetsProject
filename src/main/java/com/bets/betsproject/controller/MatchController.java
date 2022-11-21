package com.bets.betsproject.controller;

import com.bets.betsproject.model.Match;
import com.bets.betsproject.service.api.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping()
    public ResponseEntity<Match> saveMatch(@Valid @RequestBody Match match) {
        return new ResponseEntity<Match>(matchService.saveMatch(match), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Integer id) {
        return new ResponseEntity<Match>(matchService.getMatchById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable("id") Integer id,
                                             @RequestBody Match match) {
        return new ResponseEntity<Match>(matchService.updateMatch(match, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable("id") Integer id) {
        matchService.deleteMatch(id);
        return new ResponseEntity<String>("Match deleted successfully!", HttpStatus.OK);
    }
}
