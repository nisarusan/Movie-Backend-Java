package com.movie.springboot3.service;

import com.movie.springboot3.dto.TeacherDto;
import com.movie.springboot3.dto.VotesDto;
import com.movie.springboot3.model.Teacher;
import com.movie.springboot3.model.Vote;
import com.movie.springboot3.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository repos;

    public VoteService(VoteRepository repos) {
        this.repos = repos;
    }

    public VotesDto createVotes(VotesDto votesDto) {
        Vote vote =  dtoToVote(votesDto);
        vote.setPartijStemmen(votesDto.partijStemmen);
        repos.save(vote);
        votesDto.id = vote.getId();
        return votesDto;
    }

    public List<VotesDto> getAllVotes() {
        List<Vote> votes = repos.findAll();
        List<VotesDto> votesDtoList = new ArrayList<>();
        for (Vote vote : votes) {
            VotesDto votesDto = votesToDto(vote);
            votesDtoList.add(votesDto);
        }
        return votesDtoList;
    }




    public VotesDto votesToDto(Vote vote) {
        VotesDto votesDto = new VotesDto();
        votesDto.partijStemmen = vote.getPartijStemmen();
        votesDto.id = vote.getId();
        return votesDto;
    }

    public Vote dtoToVote(VotesDto votesDto) {
        Vote vote = new Vote();
        vote.setPartijStemmen(votesDto.partijStemmen);
        return vote;
    }

}
