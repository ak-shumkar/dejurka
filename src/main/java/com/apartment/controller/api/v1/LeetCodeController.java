package com.apartment.controller.api.v1;

import com.apartment.model.Rank;
import com.apartment.model.Result;
import com.apartment.repository.RankRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ranklist/")
public class LeetCodeController {

    private final RankRepository rankRepository;

    public LeetCodeController(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @GetMapping
    public List<Result> list() {
        List<Result> ranklist = new ArrayList<>();

        List<Rank> ranks = rankRepository.findAll();

        Long order = 1L;

        for(Rank rank : ranks) {
            ranklist.add(new Result(0L,rank.getUsername(), rank.getSolved()));
        }

        ranklist.sort(Comparator.comparing(Result::getSolved).reversed());

        for(Result result : ranklist){
            result.setRank(order++);
        }
        return ranklist;
    }
}
