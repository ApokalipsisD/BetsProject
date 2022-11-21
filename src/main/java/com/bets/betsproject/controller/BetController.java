package com.bets.betsproject.controller;

import com.bets.betsproject.model.Bet;
import com.bets.betsproject.service.api.BetService;
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
@RequestMapping("/bet")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping()
    public ResponseEntity<Bet> saveBet(@RequestBody Bet bet) {
        return new ResponseEntity<Bet>(betService.saveBet(bet), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Bet> getAllBets() {
        return betService.getAllBets();
    }

    @GetMapping("{id}")
    public ResponseEntity<Bet> getBetById(@PathVariable("id") Integer id) {
        return new ResponseEntity<Bet>(betService.getBetById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Bet> updateBet(@PathVariable("id") Integer id,
                                         @RequestBody Bet bet) {
        return new ResponseEntity<Bet>(betService.updateBet(bet, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBet(@PathVariable("id") Integer id) {
        betService.deleteBet(id);
        return new ResponseEntity<String>("Bet deleted successfully!", HttpStatus.OK);
    }

    //todo add get methods
}
