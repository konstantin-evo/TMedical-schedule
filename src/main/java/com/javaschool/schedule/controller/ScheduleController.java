package com.javaschool.schedule.controller;

import com.javaschool.schedule.Consumer;
import com.javaschool.schedule.model.TherapyCase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @ModelAttribute("cases")
    public List<TherapyCase> therapyCase() {
        return Consumer.list;
    }

    @GetMapping("/1")
    public String schedule(){
        return "index";
    }
}
