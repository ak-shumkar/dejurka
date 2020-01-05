package com.apartment.service;

import com.apartment.model.Rank;
import com.apartment.repository.RankRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class ScheduledTasks {

  private final RankRepository rankRepository;

  public ScheduledTasks(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Scheduled(fixedRate = 1800000)
  public void reportCurrentTime() throws IOException {
     List<Rank> rankList = rankRepository.findAll();

     for(Rank rank : rankList){
         try {
             Document doc = Jsoup.parse(new URL("https://leetcode.com/" + rank.getUsername()), 20000);
             String[] text= doc.getElementsContainingText("Solved Question").get(10).text().split("/");
             rank.setSolved(Integer.parseInt(text[0].trim()));
         }catch (Exception e){
             System.out.println("Couldn't update " + rank.getUsername());
         }
       rankRepository.save(rank);
     }
  }
}