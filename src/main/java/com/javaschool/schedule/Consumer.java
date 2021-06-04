package com.javaschool.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.schedule.model.TherapyCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Consumer {

    private final ObjectMapper mapper;
    public static List<TherapyCase> list;

    @Autowired
    public Consumer(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void processMessage(String schedule) throws JsonProcessingException {
        list = mapper.readValue(schedule, new TypeReference<List<TherapyCase>>(){});
        log.info("Schedule > " + list);
        System.out.println(list);
    }
}