package com.javaschool.schedule.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.schedule.model.TherapyCase;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@Log4j
@Service
public class ScheduleService {

    private static final String GET_SCHEDULE_URI = "http://localhost:8081/schedule";
    private Client client;

    private final ObjectMapper mapper;

    @Autowired
    public ScheduleService(ObjectMapper mapper){
        this.mapper = mapper;
    }

    public List<TherapyCase> getSchedule() throws JsonProcessingException {
        Client client = getClient();
        WebTarget target = client.target(GET_SCHEDULE_URI);
        String response = target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        List<TherapyCase> list = mapper.readValue(response, new TypeReference<>() {
        });
        return list;
    }

    public List<TherapyCase> getSchedule(String response) throws JsonProcessingException {
        return mapper.readValue(response, new TypeReference<>() {});
    }

    private Client getClient() {
        if (Objects.isNull(client)) {
            client = ClientBuilder.newClient();
        }
        return client;
    }

}
