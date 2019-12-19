package com.apartment.controller.api.v1;

import com.apartment.assembler.datatable.UserResourceAssembler;
import com.apartment.dto.ApiResponse;
import com.apartment.dto.UserDto;
import com.apartment.model.Result;
import com.apartment.model.User;
import com.apartment.resource.datatable.UserResource;
import com.apartment.service.user.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/ranklist/")
public class LeetCodeController {

    @GetMapping
    public List<Result> list() throws IOException {
        List<String> users = Arrays.asList("emli", "abdykaparkamilov", "zubaidullo","kalandar","smilerik","aykobb");

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
