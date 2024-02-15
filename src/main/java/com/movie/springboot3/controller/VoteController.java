package com.movie.springboot3.controller;


import com.movie.springboot3.dto.VotesDto;
import com.movie.springboot3.service.VoteService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/votes")
    public ResponseEntity<VotesDto> createVotes(@RequestBody VotesDto votesDto) {
        votesDto = voteService.createVotes(votesDto);
                return ResponseEntity.ok(votesDto);
    }

    @GetMapping("/votes")
    public ResponseEntity<List<VotesDto>> getAllVotes() {
        List<VotesDto> allVotes = voteService.getAllVotes();
        return ResponseEntity.ok(allVotes);
    }

}
