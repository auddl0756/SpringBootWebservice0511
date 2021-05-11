package com.roon.board.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {
    private static String template = "Hello %s";

    @GetMapping
    public String hello(@RequestParam(value="name",defaultValue = "world") String name){
        return String.format(template,name);
    }
}
