package com.javaschool.schedule.controller;

import com.javaschool.schedule.Consumer;
import com.javaschool.schedule.model.TherapyCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ScheduleController {

    @ModelAttribute("cases")
    public List<TherapyCase> therapyCase() {
        return Consumer.list;
    }

    @GetMapping("")
    public String schedule(){
        return "index";
    }
}
