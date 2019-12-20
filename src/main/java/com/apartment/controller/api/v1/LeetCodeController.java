package com.apartment.controller.api.v1;

import com.apartment.model.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ranklist/")
public class LeetCodeController {

    @GetMapping
    public List<Result> list() throws IOException {
        List<String> users = Arrays.asList("emli", "abdykaparkamilov", "zubaidullo","kalandar","smilerik","BekbolotovBolot","wwormich","ADJA","mbek","justlive","mfv");

        List<Result> ranklist = new ArrayList<>();

        Long order = 1L;
        for(String user : users) {

            Document doc = Jsoup.parse(new URL("https://leetcode.com/" + user), 20000);

            String[] text= doc.getElementsContainingText("Solved Question").get(10).text().split("/");
            ranklist.add(new Result(0L,user, Long.parseLong(text[0].trim())));
        }

        ranklist.sort(Comparator.comparing(Result::getSolved).reversed());

        for(Result result : ranklist){
            result.setRank(order++);
        }
        return ranklist;
    }
}
